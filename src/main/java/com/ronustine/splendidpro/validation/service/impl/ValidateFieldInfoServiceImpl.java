package com.ronustine.splendidpro.validation.service.impl;

import com.ronustine.splendidpro.utils.BeanUtil;
import com.ronustine.splendidpro.utils.JsonUtil;
import com.ronustine.splendidpro.validation.dao.ValidateFieldInfoDao;
import com.ronustine.splendidpro.validation.entity.dto.ValidateFieldInfoDTO;
import com.ronustine.splendidpro.validation.entity.po.ValidateFieldInfo;
import com.ronustine.splendidpro.validation.service.FieldStructureFactory;
import com.ronustine.splendidpro.validation.service.ValidateFieldInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @author ronustine
 */
@Service
public class ValidateFieldInfoServiceImpl implements ValidateFieldInfoService {

    Logger log = LoggerFactory.getLogger(ValidateFieldInfoServiceImpl.class);

    @Autowired
    FieldStructureFactory fieldStructureFactory;
    @Autowired
    ValidateFieldInfoDao validateFieldInfoDao;

    @Override
    public int analyzeJsonStructure(String interfaceId, String json) {
        log.info("为接口[{}]增加相关字段", interfaceId);
        Map<String, Object> fieldRulesMap = JsonUtil.parseJSON2Map(json);
        JsonUtil.filterList(fieldRulesMap);
        List<ValidateFieldInfo> fieldRulesList = composeStandardFields(fieldRulesMap);
        int num = 0;
        for (ValidateFieldInfo validateFieldInfo : fieldRulesList) {
            validateFieldInfo.setInterfaceId(new Long(interfaceId));
            int s = validateFieldInfoDao.insertSelective(validateFieldInfo);
            if (s >0 ) { num++; }
        }
//        int num = validateFieldInfoDao.insertCodeBatch(fieldRulesList);// 数据量大了再考虑，interfaceId要记得加
        log.info("为接口[{}]增加字段[{}]个", interfaceId, num);
        return num;
    }


    @Override
    public Map<String, ValidateFieldInfo> findMapByInterfaceId(String interfaceId) {
        List<ValidateFieldInfo> validateFieldInfos = findByInterfaceId(interfaceId);
        Map<String, ValidateFieldInfo> pathAndField = new HashMap(16);
        log.info("接口interfaceId[{}]中字段转为Map形式，用于查找", interfaceId);
        for (ValidateFieldInfo validateFieldInfoTemp : validateFieldInfos){
            StringBuilder fieldPath = new StringBuilder("");
            if(null != validateFieldInfoTemp.getParentField()){
                fieldPath.append(validateFieldInfoTemp.getParentField()).append(".");
            }
            fieldPath.append(new StringBuilder(validateFieldInfoTemp.getFieldCode()));
            // TODO 只用了ParentField，和FieldCode，故此字段在第三层时,路径是不完整的，相同时put操作会覆盖，需要数据库增加字段path
            pathAndField.put(fieldPath.toString(), validateFieldInfoTemp);
        }
        return pathAndField;
    }

    @Override
    public Map<String, List<ValidateFieldInfo>> findParentWithSubFieldByInterfaceId(String interfaceId) {
        Example example = new Example(ValidateFieldInfo.class);
        Example.Criteria criteria = example.createCriteria().
                andEqualTo("interfaceId", interfaceId).
                andEqualTo("isParent", "1");
        List<ValidateFieldInfo> validateFieldsPInfo = validateFieldInfoDao.selectByExample(example);

        Map<String, List<ValidateFieldInfo>> childs = new LinkedHashMap<>();
        for (ValidateFieldInfo validateFieldInfo : validateFieldsPInfo){
            String parentCode = validateFieldInfo.getFieldCode();
            Example exampleT = new Example(ValidateFieldInfo.class);
            Example.Criteria criteriaT = exampleT.createCriteria().
                    andEqualTo("interfaceId", interfaceId).
                    andEqualTo("parentField", parentCode).
                    andEqualTo("isParent", "0");
            List<ValidateFieldInfo> validateFieldsChildInfo = validateFieldInfoDao.selectByExample(exampleT);
            childs.put(parentCode, validateFieldsChildInfo);
        }
        return childs;
    }

    @Override
    public List<ValidateFieldInfo> findByInterfaceId(String interfaceId) {
        ValidateFieldInfo validateFieldInfo = new ValidateFieldInfo();
        validateFieldInfo.setInterfaceId(new Long(interfaceId));
        return validateFieldInfoDao.select(validateFieldInfo);
    }

	@Override
    public ValidateFieldInfoDTO getStructureByInterfaceId (Long interfaceId){
		Example example = new Example(ValidateFieldInfo.class);
		Example.Criteria criteria = example.createCriteria().
				andEqualTo("interfaceId", interfaceId).
				andIsNull("parentField");
		List<ValidateFieldInfo> validateFieldsTopInfo = validateFieldInfoDao.selectByExample(example);
		ValidateFieldInfo validateFieldInfo = validateFieldsTopInfo.get(0);
		ValidateFieldInfoDTO validateFieldInfoDTO = new ValidateFieldInfoDTO();
		BeanUtil.BeanCopier(validateFieldInfo, validateFieldInfoDTO);
		getChildren(validateFieldInfoDTO);
		return validateFieldInfoDTO;
	}

	private ValidateFieldInfoDTO getChildren(ValidateFieldInfoDTO validateFieldInfoDTO) {
//    	log.info("获取[{}]的子节点", validateFieldInfoDTO.getFieldName());
		Example example = new Example(ValidateFieldInfo.class);
		Example.Criteria criteria = example.createCriteria().
				andEqualTo("interfaceId", validateFieldInfoDTO.getInterfaceId()).
				andEqualTo("parentField", validateFieldInfoDTO.getFieldCode());
		List<ValidateFieldInfo> validateFieldsChildInfos = validateFieldInfoDao.selectByExample(example);
		List<ValidateFieldInfoDTO> validateFieldInfoDTOChildren = new ArrayList<>();
		for (ValidateFieldInfo validateFieldInfo : validateFieldsChildInfos) {
			ValidateFieldInfoDTO validateFieldInfoDTONew = new ValidateFieldInfoDTO();
			BeanUtil.BeanCopier(validateFieldInfo, validateFieldInfoDTONew);
			validateFieldInfoDTOChildren.add(validateFieldInfoDTONew);
			// TODO 魔法值
			if ("1".equals(validateFieldInfo.getIsParent())) {
				getChildren(validateFieldInfoDTONew);
			}
		}
		validateFieldInfoDTO.setValidateFieldInfoDTOList(validateFieldInfoDTOChildren);
		return validateFieldInfoDTO;
	}


    /**
     * 将map内容转成List
     * @param fieldRulesMap
     * @return
     */
    private List<ValidateFieldInfo> composeStandardFields(Map<String, Object> fieldRulesMap) {
        log.info("开始解析standardField");
        return composeStandardFields(fieldRulesMap, new ArrayList(), null, 1);
    }

    /**
     * 递归，將fieldRulesMap 转到fieldRulesList中，用于存入数据库
     * @param fieldRulesMap 字段规则-Map形式
     * @param fieldRulesList 字段规则-List形式
     * @param currentParentField 当前父节点是哪个
     * @param currentIndex 当前是第几层
     * @return
     */
    private List<ValidateFieldInfo> composeStandardFields(Map<String, Object> fieldRulesMap, List<ValidateFieldInfo> fieldRulesList, String currentParentField, int currentIndex){
        String tab = "";
        for (int i = 0; i < currentIndex; i++){ tab += "      "; }
        log.info("{}正在解析standardField，当前父节点currentParentField为[{}]， 第[{}]层", tab, currentParentField, currentIndex);
        Iterator entries = fieldRulesMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String)entry.getKey();
            Object value = entry.getValue();
            ValidateFieldInfo validateFieldInfo = new ValidateFieldInfo();
            validateFieldInfo.setFieldCode(key);
            validateFieldInfo.setIndexLevel(currentIndex);
            validateFieldInfo.setParentField(currentParentField);
            //TODO 魔法值
            if(value instanceof String) {
                validateFieldInfo.setIsParent("0");
                validateFieldInfo.setFieldName((String) value);
            } else if (value instanceof Map) {
                validateFieldInfo.setIsParent("1");
                composeStandardFields((Map<String, Object>)value, fieldRulesList, key, currentIndex +1);
            }
            fieldRulesList.add(validateFieldInfo);
        }
        log.info("{}本次共处理[{}]个节点", tab, fieldRulesList.size());
        return fieldRulesList;
    }
}
