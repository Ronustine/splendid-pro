package com.ronustine.splendidpro.validation.strategy;

import com.ronustine.splendidpro.common.constant.ValidationFieldEnum;
import com.ronustine.splendidpro.common.constant.YesNoEnum;
import com.ronustine.splendidpro.common.exception.SpServiceException;
import com.ronustine.splendidpro.utils.SpringContextHolder;
import com.ronustine.splendidpro.utils.StringCalculate;
import com.ronustine.splendidpro.validation.entity.dto.ValidateDetailDTO;
import com.ronustine.splendidpro.validation.entity.po.ValidateFieldInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 接口校验
 * @author ronustine
 */
@Component
public abstract class ParameterValidator {

	static Logger log = LoggerFactory.getLogger(ParameterValidator.class);

	public static final Map<String, ParameterValidator> PARAMETER_VALIDATOR_MAP = new ConcurrentHashMap<>();
	final static String CLASS_DEFAULT = "DefaultInterfaceFieldValidator";

	static final char[] ACCEPTABLE_PUNCTUATION_CHAR = "（）()- ".toCharArray();

	/**
	 * 中文名称校验，允许的仅为：中文、以及常量 ACCEPTABLE_PUNCTUATION_CHAR 列出的符号
	 *
	 * @param str
	 * @return
	 */
	public static boolean isZhName(String str) {
		char[] chars = str.toCharArray();
		for (char oChar: chars) {
			// 0~9
			if ((oChar >= 48 && oChar <= 57)) {
				continue;
			}

			// 可接受的符号
			boolean acceptable = false;
			for (char c: ACCEPTABLE_PUNCTUATION_CHAR) {
				if (c == oChar) {
					acceptable = true;
					break;
				}
			}

			// 中文
			Character.UnicodeBlock ub = Character.UnicodeBlock.of(oChar);
			if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) {
				continue;
			}

			if (acceptable) {
				continue;
			}
			// 既不是中文、也不是可接受的标点符号
			return false;
		}
		return true;
	}

	/**
	 * 中英文名称校验，允许的仅为：中文、A~Z a~z、以及常量 ACCEPTABLE_PUNCTUATION_CHAR 列出的符号
	 * @param str
	 * @return
	 */
	public static boolean isEnZhName(String str) {
		char[] chars = str.toCharArray();
		for (char oChar: chars) {
			if ((oChar >= 48 && oChar <= 57) ||
					(oChar >= 65 && oChar <= 90) ||
					(oChar >= 97 && oChar <= 122)) {
				// 0~9 A~Z a~z
				continue;
			}

			boolean acceptable = false;
			for (char c: ACCEPTABLE_PUNCTUATION_CHAR) {
				if (c == oChar) {
					acceptable = true;
					break;
				}
			}

			Character.UnicodeBlock ub = Character.UnicodeBlock.of(oChar);
			if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) {
				continue;
			}

			if (acceptable) {
				continue;
			}
			// 既不是中文、也不是可接受的标点符号
			return false;
		}
		return true;
	}

	/**
	 * 正则校验
	 * @param str
	 * @return
	 */
	public static boolean reExCheck(String str, String ex) {
		return str.matches(ex);
	}

	/**
     * 是否数值
     */
    public static boolean isNumber (String numStr){
        try{
            BigDecimal temp = new BigDecimal(numStr);
        }catch (Exception e){
            return false;
        }
        return true;
    }

	private static Map<String, String> dateFormatRegex = new HashMap<>(16);
	static {
		// yyyy-MM-dd HH:mm:ss
		dateFormatRegex.put(ValidationFieldEnum.DATA_DATE1.getCode(), "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}");
		// yyyyMMdd
		dateFormatRegex.put(ValidationFieldEnum.DATA_DATE2.getCode(), "[0-9]{4}[0-9]{2}[0-9]{2}");
		// yyyy-MM-dd
		dateFormatRegex.put(ValidationFieldEnum.DATA_DATE3.getCode(), "[0-9]{4}-[0-9]{2}-[0-9]{2}");
	}

	/**
	 * 日期格式是否为timeFormat
	 */
	public static boolean isDate(String timeStr, String timeFormat){
		String regex = dateFormatRegex.get(timeFormat);
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(timeStr);
		boolean dateFlag = m.matches();
		if (!dateFlag) {
			return false;
		}
		return formatTime(timeStr, ValidationFieldEnum.getDesc(timeFormat));
	}

	/**
	 * 以严格的方式格式化时间
	 * @param timeStr
	 * @param format
	 * @return
	 */
	private static boolean formatTime(String timeStr, String format){
		DateFormat formatter = new SimpleDateFormat(format);
		formatter.setLenient(false);
		try{
			formatter.parse(timeStr);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	/**
	 * 将num 转BigDecimal
	 * @param num
	 * @return
	 */
	protected static BigDecimal discriminateNum(Object num){
		BigDecimal amount = new BigDecimal(0);
		if (num instanceof BigDecimal){
			amount = (BigDecimal) num;
		}else if (num instanceof String){
			if (StringUtils.isBlank((String) num)) {
				return amount;
			}
			amount = new BigDecimal((String) num);
		}else if (num instanceof Integer){
			if (null == (Integer) num) {
				return amount;
			}
			amount = new BigDecimal((Integer) num);
		}
		return amount;
	}

	/**
	 * 获取校验使用的validator
	 * @return
	 */
	public static ParameterValidator getValidator(String standardCode, String interfaceCode) {
		String baseValidatorName = CLASS_DEFAULT;
		if (false){
			baseValidatorName = "asd";
		}

		StringBuilder validatorName = new StringBuilder(StringCalculate.firstCapLowerCase(standardCode)).append(baseValidatorName);
		if (CLASS_DEFAULT.equals(baseValidatorName)) {
			validatorName = new StringBuilder(StringCalculate.firstCapLowerCase(baseValidatorName));
		}
		ParameterValidator parameterValidator = PARAMETER_VALIDATOR_MAP.get(validatorName.toString());

		if(parameterValidator == null){
			try {
				parameterValidator = (ParameterValidator) SpringContextHolder
						.getBean(validatorName.toString());
			} catch (Exception e) {
				log.error("未找到此校验类[{}]，不再执行", validatorName);
				throw new SpServiceException("未找到此校验类：" + validatorName);
			}

			if (parameterValidator != null) {
				PARAMETER_VALIDATOR_MAP.put(validatorName.toString(), parameterValidator);
			}
		}

		return parameterValidator;
	}

	/**
	 * 接口出参校验
	 * @param json 数据
	 * @param validateFieldInfos 字段标准
	 * @param detail 接口细节
	 */
	public abstract void interfaceCheck(Map<String, Object> json, Map<String, ValidateFieldInfo> validateFieldInfos,
										Map<String, ValidateDetailDTO> detail);


	/**
	 * 是否有值，空、空字符串 统一认为无值
	 * @param value
	 * @return
	 */
	public static boolean hasValue(Object value) {
		if (null == value) {
			return false;
		} else if (value instanceof List) {
			List valueList = (List) value;
			if (0 < valueList.size()) {
				return true;
			}
		} else if (value instanceof Map) {
			Map valueMap = (Map) value;
			if (0 < valueMap.size()) {
				return true;
			}
		} else if (value instanceof String) {
			String valueS = (String) value;
			if(StringUtils.isNotBlank(valueS)) {
				return true;
			}
		} else if (value instanceof BigDecimal) {
			return true;
		} else if (value instanceof Integer) {
			return true;
		} else if (value instanceof Long) {
			return true;
		}
		return false;
	}

	/**
	 * 设置validateFieldInfo，记录每个字段的校验失败原因
	 * @param key 入参
	 * @param validateFieldInfo 字段格式
	 * @param detail 字段细节对象
	 * @param validationFieldEnum 接口参数异常枚举
	 */
	public static void setValidationDetailInfoDTO(String key, ValidateFieldInfo validateFieldInfo, Map<String, ValidateDetailDTO> detail,
												  ValidationFieldEnum validationFieldEnum) {
		setValidationDetailInfoDTO(key, validateFieldInfo, detail, validationFieldEnum, "", null);
	}

	/**
	 * 设置validateFieldInfo，记录每个字段的校验失败原因
	 * @param key 入参
	 * @param validateFieldInfo 字段格式
	 * @param detail 字段细节对象
	 * @param validationFieldEnum 接口参数异常枚举
	 * @param extraReason 额外理由
	 */
	public static void setValidationDetailInfoDTOWithExtraReason(String key, ValidateFieldInfo validateFieldInfo, Map<String, ValidateDetailDTO> detail,
												  ValidationFieldEnum validationFieldEnum, String extraReason) {
		setValidationDetailInfoDTO(key, validateFieldInfo, detail, validationFieldEnum, extraReason, null);
	}

	/**
	 * 设置validateFieldInfo，记录每个字段的校验失败原因
	 * @param key 入参
	 * @param validateFieldInfo 字段格式
	 * @param detail 字段细节对象
	 * @param validationFieldEnum 接口参数异常枚举
	 * @param comment 备注
	 */
	public static void setValidationDetailInfoDTOWithComment(String key, ValidateFieldInfo validateFieldInfo, Map<String, ValidateDetailDTO> detail,
																 ValidationFieldEnum validationFieldEnum, String comment) {
		setValidationDetailInfoDTO(key, validateFieldInfo, detail, validationFieldEnum, "", comment);
	}

	/**
	 * 设置validateFieldInfo，记录每个字段的校验失败原因
	 * @param key 入参
	 * @param validateFieldInfo 字段格式
	 * @param detail 字段细节对象
	 * @param validationFieldEnum 接口参数异常枚举
	 * @param comment 备注
	 */
	public static void setValidationDetailInfoDTO(String key, ValidateFieldInfo validateFieldInfo, Map<String, ValidateDetailDTO> detail,
												  ValidationFieldEnum validationFieldEnum, String extraReason, String comment) {
		ValidateDetailDTO ValidateDetailDTO = detail.get(key);
		if (null == ValidateDetailDTO){
			ValidateDetailDTO = new ValidateDetailDTO();
			ValidateDetailDTO.setFieldCode(key);
			ValidateDetailDTO.setFieldName(validateFieldInfo.getFieldName());

			//		log.info("	设置Detail内容：字段[{}][{}]校验不通过，[{}]", validateFieldInfo.getFieldName(), key, interfaceFieldEnum.getQueryDesc());
			ValidateDetailDTO.setPass(YesNoEnum.NO.getBool());
			ValidateDetailDTO.setFieldId(validateFieldInfo.getId());
			detail.put(key, ValidateDetailDTO);
		}
		StringBuilder fullReason = new StringBuilder(validationFieldEnum.getDesc());
		if (StringUtils.isNotBlank(extraReason)) {
			// 拼接补充原因
			fullReason.append("：").append(extraReason);
		}
		if (null == ValidateDetailDTO.getReason()){
			List<String> validateCodeList = new ArrayList<>();
			validateCodeList.add(validationFieldEnum.getCode());
			ValidateDetailDTO.setValidateCodeList(validateCodeList);
			ValidateDetailDTO.setReason(fullReason.toString());
		} else {
			// 数据库有错误说明
			if (!ValidateDetailDTO.getReason().contains(fullReason.toString())) {
				// 不重复
				ValidateDetailDTO.getValidateCodeList().add(validationFieldEnum.getCode());
				ValidateDetailDTO.setReason(
						new StringBuilder(ValidateDetailDTO.getReason()).append(ValidationFieldEnum.REASON_APPEND).
								append(fullReason.toString()).toString());
			}
		}
		if (StringUtils.isNotBlank(comment)) {
			// 有传备注
			String commentDB = ValidateDetailDTO.getComments();
			if (StringUtils.isBlank(commentDB)) {
				// 数据库无备注信息
				ValidateDetailDTO.setComments(comment);
			} else {
				// 数据库有备注信息
				if (!commentDB.contains(comment)) {
					// 不重复
					ValidateDetailDTO.setComments(new StringBuilder(ValidateDetailDTO.getComments()).
							append(ValidationFieldEnum.REASON_APPEND).append(comment).toString());
				}
			}
		}
	}
}
