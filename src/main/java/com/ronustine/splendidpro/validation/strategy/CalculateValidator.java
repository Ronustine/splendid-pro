package com.ronustine.splendidpro.validation.strategy;

import com.alibaba.fastjson.JSON;

import com.ronustine.splendidpro.common.constant.ValidationFieldEnum;
import com.ronustine.splendidpro.common.exception.SpBusinessException;
import com.ronustine.splendidpro.utils.JsonUtil;
import com.ronustine.splendidpro.utils.ThreadLocalMapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类LogicalOperationValidator的实现描述：利用JavaScript引擎校验逻辑、四则运算的规则
 *
 * @author ronustine 2019/4/26 17:14
 */
@Service
public class CalculateValidator {

    Logger log = LoggerFactory.getLogger(CalculateValidator.class);

    private static final Pattern VARIANT_PATTERN = Pattern.compile("\\$([\\w\\.]+)");

    private static final String THIS_PARAM = "this";

    private static final String SCRIPT_ENGINE_JS = "SCRIPT_ENGINE_JS";

    /**
     * 逻辑运算
     * @param formula 表达式
     * @param currentCode 当前字段Code
     * @param validateData 当前字段所在节点
     * @return
     */
    public Boolean logicalCalculate(String formula, String currentCode, Map<String, Object> validateData){
        StringBuilder newFormula = new StringBuilder();
        Boolean result = null;
        try {
            newFormula = toTrueFormula(formula, currentCode, validateData);
            result = (Boolean)getScriptEngineJs().eval(newFormula.toString());
        } catch (SpBusinessException e) {
            log.info("替换变量失败，跳过此条校验[{}]", e);
        } catch (ScriptException e) {
            log.info("计算出现错误[{}]", newFormula.toString());
            log.info("计算出现错误，异常[{}]", e);
        }
        return result;
    }

    /**
     * 数值运算
     * @param formula 表达式
     * @param currentCode 当前字段Code
     * @param validateData 当前字段所在节点
     * @return
     */
    public BigDecimal calculate(String formula, String currentCode, Map<String, Object> validateData){
        StringBuilder newFormula = toTrueFormula(formula, currentCode, validateData);
        BigDecimal result = null;
        try {
            // 注：解决JS浮点计算精度问题，2位精度，四舍五入
            Object resultO = getScriptEngineJs().eval(newFormula.toString());
            if (null == resultO) {
                return null;
            }
            result = new BigDecimal((Double)resultO).setScale(2, RoundingMode.HALF_UP);
        } catch (ScriptException e) {
            log.info("计算出现错误[{}]", newFormula.toString());
            log.info("计算出现错误，异常[{}]", e);
        }
        return result;
    }

    /**
     * 组装成有值的表达式
     * @param formula
     * @param validateData
     * @return
     */
    private StringBuilder toTrueFormula (String formula, String currentCode, Map<String, Object> validateData) {
        Matcher matcher = VARIANT_PATTERN.matcher(formula);
        List<String> formulaParams = new ArrayList<>();
        List<String> fieldParams = new ArrayList<>();
        // 获取所有$开头的变量
        while(matcher.find()){
            formulaParams.add(matcher.group(0));
            fieldParams.add(matcher.group(1));
        }

        StringBuilder newFormula = new StringBuilder(formula);
        for (int i = 0; i< fieldParams.size(); i++) {
            String formulaParam = formulaParams.get(i);
            String fieldParam = fieldParams.get(i);
            int startIndex = newFormula.indexOf(formulaParam);
            int endIndex = startIndex + formulaParam.length();
            String value;
            if (THIS_PARAM.equals(fieldParam)) {
                // this,用当前校验字段
                value = getCurrentValidateDataValue(currentCode, validateData);
            } else {
                value = getCurrentValidateDataValue(fieldParam.toLowerCase(), validateData);
            }
            if (null == value) {
                value = "null";
            } else {
                value = "'" + value + "'";
            }
            newFormula.replace(startIndex, endIndex, value);
        }
        return newFormula;
    }

    /**
     * 获取前置条件字段的当前值
     * @param fieldCode 字段码
     * @param validateData 当前校验数据
     * @return
     */
    private String getCurrentValidateDataValue(String fieldCode, Map<String, Object> validateData) {
        Object value;
        if (fieldCode.contains(ValidationFieldEnum.FIELD_CODE_APPEND)) {
            // 获取跨层级的前置条件当前值
            Map validateDataFlat = getContextValidateData();
            value = validateDataFlat.get(fieldCode);
        } else {
            // 获取同层级的前置条件当前值
            value = validateData.get(fieldCode);
        }
        if (null == value) {
            return null;
        }
        return Objects.toString(value);
    }

    private Map getContextValidateData() {
        return JsonUtil.flatTheJson2Map(JSON.toJSONString(ThreadLocalMapUtil.get(ValidationFieldEnum.VALIDATE_DATA_CACHE)), new HashMap());
    }

    /**
     * 建立线程封闭的JS引擎
     * @return
     */
    private static ScriptEngine getScriptEngineJs() {
        Object scriptEngineJs = ThreadLocalMapUtil.get(SCRIPT_ENGINE_JS);
        if (null != scriptEngineJs) {
            return (ScriptEngine)scriptEngineJs;
        }
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        ThreadLocalMapUtil.set(SCRIPT_ENGINE_JS, engine);
        return engine;
    }

}
