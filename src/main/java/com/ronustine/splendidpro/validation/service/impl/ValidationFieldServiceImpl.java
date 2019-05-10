package com.ronustine.splendidpro.validation.service.impl;

import com.alibaba.fastjson.JSON;
import com.ronustine.splendidpro.common.constant.ValidationFieldEnum;
import com.ronustine.splendidpro.common.constant.ValidatorTypeEnum;
import com.ronustine.splendidpro.utils.BeanUtil;
import com.ronustine.splendidpro.utils.JsonUtil;
import com.ronustine.splendidpro.utils.ThreadLocalMapUtil;
import com.ronustine.splendidpro.validation.entity.dto.ValidateFieldInfoDTO;
import com.ronustine.splendidpro.validation.entity.dto.ValidateResult;
import com.ronustine.splendidpro.validation.entity.dto.ValidateDetailDTO;
import com.ronustine.splendidpro.validation.entity.po.ValidateValidator;
import com.ronustine.splendidpro.validation.entity.po.ValidateFieldInfo;
import com.ronustine.splendidpro.validation.entity.po.ValidateInterface;
import com.ronustine.splendidpro.validation.entity.po.ValidateStandardInfo;
import com.ronustine.splendidpro.validation.service.*;
import com.ronustine.splendidpro.validation.strategy.CalculateValidator;
import com.ronustine.splendidpro.validation.strategy.ParameterValidator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * ValidationFieldServiceImpl的实现描述：数据校验核心
 *
 * @author ronustine
 */
@Component
public class ValidationFieldServiceImpl implements ValidationFieldService {

    Logger log = LoggerFactory.getLogger(ValidationFieldServiceImpl.class);

    @Autowired
	FieldStructureFactory fieldStructureFactory;
    @Autowired
	ValidateInterfaceService validateInterfaceService;
	@Autowired
	ValidateStandardInfoService validateStandardInfoService;
	@Autowired
	ValidateFieldInfoService validateFieldInfoService;
	@Autowired
	ValidateValidatorService validatorService;
	@Autowired
	CalculateValidator calculateValidator;

	@Override
	public ValidateResult getVerifyResponseMsg(String standardCode, String interfaceCode, String data) {
		ValidateStandardInfo validateStandardInfo = validateStandardInfoService.getByCode(standardCode);
		ValidateInterface validateInterface = validateInterfaceService.getInterface(validateStandardInfo.getId(), interfaceCode);
		Map<String, ValidateDetailDTO> ValidationDetailInfoDTOMap = simpleVerifyResponse(validateInterface, data);
		StringBuilder validationInfo = new StringBuilder();
		StringBuilder commentInfo = new StringBuilder();

		if (0 == ValidationDetailInfoDTOMap.size()) {
			return null;
		}
		// 拼接非格式化数据
		for (ValidateDetailDTO detailInfoDTO: ValidationDetailInfoDTOMap.values()) {
			validationInfo.append("[").append(detailInfoDTO.getFieldName()).append("]").append(" - ").
					append("[").append(detailInfoDTO.getFieldCode()).append("]").
					append(detailInfoDTO.getReason()).append("；").append(System.getProperty("line.separator"));
			if (StringUtils.isNotBlank(detailInfoDTO.getComments())) {
				// 备注信息
				commentInfo.append("[").append(detailInfoDTO.getFieldName()).append("]").append(" - ").
						append("[").append(detailInfoDTO.getFieldCode()).append("]").
						append(detailInfoDTO.getComments()).append("；").append(System.getProperty("line.separator"));
			}
		}
		return ValidateResult.builder().detailInfoDTOList(new ArrayList<>(ValidationDetailInfoDTOMap.values())).
				validationInfo(validationInfo.toString()).commentInfo(commentInfo.toString()).build();
	}

	/**
	 * 校验主方法
	 * @param validateInterface
	 * @param json
	 * @return
	 */
	private Map<String, ValidateDetailDTO> simpleVerifyResponse(ValidateInterface validateInterface, String json) {
		ValidateStandardInfo validateStandardInfo = validateStandardInfoService.getById(validateInterface.getStandardId());
		Map<String, ValidateDetailDTO> details = new LinkedHashMap<>();

		Map<String, ValidateFieldInfo> ValidateFieldInfos = fieldStructureFactory.getFieldStructure(String.valueOf(validateInterface.getId()));

		// 带层级的数据，可校验带关系字段的数据
		Map<String, Object> jsonMap = JsonUtil.parseJSON2Map(json);
		ValidateFieldInfoDTO validateFieldInfoDTO = validateFieldInfoService.getStructureByInterfaceId(validateInterface.getId());
		Boolean canContinue = traversalStructure(jsonMap, validateFieldInfoDTO, details);
		log.info("字段基本校验情况：[{}]", canContinue);
		if (canContinue) {
			// 基本格式对了再进行复杂校验
			ParameterValidator parameterValidator = ParameterValidator.getValidator(validateStandardInfo.getStandardCode(), validateInterface.getInterfaceCode());
			parameterValidator.interfaceCheck(jsonMap, ValidateFieldInfos, details);
		}
		return details;
	}

	/**
	 * 单字段校验
	 * @param medicalData 数据
	 * @param validateFieldInfoDTO 字段规则
	 * @param details 校验信息对象
	 */
	private Boolean traversalStructure(Map<String, Object> medicalData, ValidateFieldInfoDTO validateFieldInfoDTO,
									   Map<String, ValidateDetailDTO> details){
		return traversalStructure(medicalData, validateFieldInfoDTO, details, setContextValidateData());
	}
	/**
	 * 单字段校验
	 * @param medicalData 数据
	 * @param validateFieldInfoDTO 字段规则
	 * @param details 校验信息对象
	 */
	private Boolean traversalStructure(Map<String, Object> medicalData, ValidateFieldInfoDTO validateFieldInfoDTO,
                                       Map<String, ValidateDetailDTO> details, Map<String, Object> contextMap){
		Boolean continueFlag = true;
		if (ValidationFieldEnum.DATA_LIST.getCode().equals(validateFieldInfoDTO.getDataType())) {
			log.info("节点[{}]为列表[{}] - 每个[{}]有[{}]个子节点，进入解析",validateFieldInfoDTO.getFieldName(), validateFieldInfoDTO.getDataType(),
					validateFieldInfoDTO.getFieldCode(), validateFieldInfoDTO.getValidateFieldInfoDTOList().size());
			List<ValidateFieldInfoDTO> subStandardFieldList = validateFieldInfoDTO.getValidateFieldInfoDTOList();

			Object subDataObject = medicalData.get(validateFieldInfoDTO.getFieldCode());
			if (! (subDataObject instanceof List)) {
				checkParam(medicalData, validateFieldInfoDTO, details);
				if (ValidationFieldEnum.NULL.getCode().equals(validateFieldInfoDTO.getFieldLevel())) {
					return true;
				}
				return false;
			}
			List<Map<String, Object>> subDataList = (List)subDataObject;
			if (0 == subDataList.size()) {
				checkParam(medicalData, validateFieldInfoDTO, details);
				if (ValidationFieldEnum.NULL.getCode().equals(validateFieldInfoDTO.getFieldLevel())) {
					return true;
				}
				return false;
			}
			for (Map<String, Object> subData : subDataList) {
				contextMap.put(validateFieldInfoDTO.getFieldCode(), new HashMap(subData));
				for (ValidateFieldInfoDTO subField : subStandardFieldList) {
					if ( !traversalStructure(subData, subField, details, (Map<String, Object>)contextMap.get(validateFieldInfoDTO.getFieldCode()))) {
						continueFlag = false;
					}
				}
			}
		} else if (ValidationFieldEnum.DATA_OBJECT.getCode().equals(validateFieldInfoDTO.getDataType())) {
			log.info("节点[{}]为对象[{}] - 此[{}]有[{}]个子节点，进入解析",validateFieldInfoDTO.getFieldName(), validateFieldInfoDTO.getDataType(),
					validateFieldInfoDTO.getFieldCode(), validateFieldInfoDTO.getValidateFieldInfoDTOList().size());
			List<ValidateFieldInfoDTO> subStandardFieldList = validateFieldInfoDTO.getValidateFieldInfoDTOList();

			Object subDataObject = medicalData.get(validateFieldInfoDTO.getFieldCode());
			if ( !(subDataObject instanceof Map)) {
				checkParam(medicalData, validateFieldInfoDTO, details);
				if (ValidationFieldEnum.NULL.getCode().equals(validateFieldInfoDTO.getFieldLevel())) {
					return true;
				}
				return false;
			}
			Map<String, Object> subData = (Map)subDataObject;
			if (0 == subData.size()) {
				checkParam(medicalData, validateFieldInfoDTO, details);
				if (ValidationFieldEnum.NULL.getCode().equals(validateFieldInfoDTO.getFieldLevel())) {
					return true;
				}
				return false;
			}
			contextMap.put(validateFieldInfoDTO.getFieldCode(), new HashMap(subData));
			for (ValidateFieldInfoDTO subField : subStandardFieldList) {
				if ( !traversalStructure(subData, subField, details, (Map<String, Object>)contextMap.get(validateFieldInfoDTO.getFieldCode()))) {
					continueFlag = false;
				}
			}
		} else {
			// 解析此处应为单字段
			log.info("对节点[{}] - [{}][{}]开始校验", validateFieldInfoDTO.getFieldName(), validateFieldInfoDTO.getParentField(),
					validateFieldInfoDTO.getFieldCode());
			checkParam(medicalData, validateFieldInfoDTO, details);
		}
		return continueFlag;
	}

	/**
	 * 保存校验内容的上下文
	 * @return
	 */
	private Map setContextValidateData() {
		Map contextValidateMap = new HashMap();
		ThreadLocalMapUtil.set(ValidationFieldEnum.VALIDATE_DATA_CACHE, contextValidateMap);
		return contextValidateMap;
	}

	/**
	 * 数据检查
	 * @param medicalData 数据
	 * @param validateFieldInfoDTO 字段规则
	 * @param details 校验信息对象
	 */
	private void checkParam(Map<String, Object> medicalData, ValidateFieldInfoDTO validateFieldInfoDTO, Map<String, ValidateDetailDTO> details) {
		StringBuilder key = new StringBuilder();
		if (StringUtils.isNotBlank(validateFieldInfoDTO.getParentField())) {
			key.append(validateFieldInfoDTO.getParentField()).append(".");
		}
		key.append(validateFieldInfoDTO.getFieldCode());
		ValidateFieldInfo validateFieldInfo = new ValidateFieldInfo();
		BeanUtil.BeanCopier(validateFieldInfoDTO, validateFieldInfo);

		Object value = medicalData.get(validateFieldInfoDTO.getFieldCode());
		if ( !(ValidationFieldEnum.DATA_LIST.getCode().equals(validateFieldInfoDTO.getDataType()) ||
				ValidationFieldEnum.DATA_OBJECT.getCode().equals(validateFieldInfoDTO.getDataType())) ) {
			// 既不是List也不是Object
			String valueS = "";
			if (null != value) {
				valueS = value.toString();
			}
			singleFieldDataCheck(key.toString(), valueS, validateFieldInfo, details);
		}
		checkFormat(key.toString(), value, validateFieldInfo, details);
		checkNotNull(key.toString(), value, validateFieldInfo, details);
		checkMissing(key.toString(), medicalData, validateFieldInfo, details);
		checkValidators(key.toString(), medicalData, validateFieldInfo, details);

	}

	/**
	 * 节点格式校验，List
	 * @param key 数据的键
	 * @param value 数据的值
	 * @param validateFieldInfo 字段规则规则的值
	 * @param detail 字段细节对象
	 */
	private void checkFormat(String key, Object value, ValidateFieldInfo validateFieldInfo, Map<String, ValidateDetailDTO> detail) {
		if (null == value) {
			// 空值无需判断
			return;
		}
		if (ValidationFieldEnum.DATA_LIST.getCode().equals(validateFieldInfo.getDataType())) {
			if (value instanceof List) {
				return;
			}
			// 不是List
			ParameterValidator.setValidationDetailInfoDTO(key, validateFieldInfo, detail, ValidationFieldEnum.DATA_FORMAT_ERROR);
		}

		if (ValidationFieldEnum.DATA_OBJECT.getCode().equals(validateFieldInfo.getDataType())) {
			if (value instanceof Map) {
				return;
			}
			// 不是Map
			ParameterValidator.setValidationDetailInfoDTO(key, validateFieldInfo, detail, ValidationFieldEnum.DATA_FORMAT_ERROR);
		}
	}

	/**
	 * 缺失节点校验，不存在则记录至details
	 * @param key 数据的键
	 * @param medicalData 数据
	 * @param validateFieldInfo 字段规则
	 * @param details 校验信息对象
	 */
	private void checkMissing(String key, Map<String, Object> medicalData, ValidateFieldInfo validateFieldInfo, Map<String, ValidateDetailDTO> details) {
		boolean containKey = medicalData.containsKey(validateFieldInfo.getFieldCode());
		if (!ValidationFieldEnum.NOT_NULL_1.getCode().equals(validateFieldInfo.getFieldLevel())) {
			return;
		}
		if (containKey) {
			return;
		}
		// 此字段是非空1，且不包含字段
		log.info("[{}][{}]", key, ValidationFieldEnum.NOT_FOUND.getDesc());
		ParameterValidator.setValidationDetailInfoDTO(key, validateFieldInfo, details, ValidationFieldEnum.NOT_FOUND);
	}

    /**
     * 检查key对应的value，从规则validateFieldInfo获取是否允许为空，不允许则记录至detail
     * @param key 数据的键
     * @param value 数据的值
     * @param validateFieldInfo 字段规则规则的值
     * @param detail 字段细节对象
     */
    private void checkNotNull(String key, Object value, ValidateFieldInfo validateFieldInfo, Map<String, ValidateDetailDTO> detail){
        if(!ValidationFieldEnum.NOT_NULL_1.getCode().equals(validateFieldInfo.getFieldLevel())){
            return;
        }
		if (ParameterValidator.hasValue(value)) {
        	return;
		}

        log.info("[{}][{}][{}]", key, ValidationFieldEnum.NOT_NULL.getDesc(), JSON.toJSON(value));
		ParameterValidator.setValidationDetailInfoDTO(key, validateFieldInfo, detail, ValidationFieldEnum.NOT_NULL);
	}

    /**
     * 单一字段校验，检查key对应的value，从规则validateFieldInfo校验数据
     * @param key 数据的键
     * @param value 数据的值
     * @param validateFieldInfo 字段规则规则的值
     * @param detail 字段细节对象
     */
    private void singleFieldDataCheck(String key, String value, ValidateFieldInfo validateFieldInfo, Map<String, ValidateDetailDTO> detail){
        if(StringUtils.isEmpty(value)){
            return;
        }
        log.info("[{}]-[{}]数据类型为[{}]，字段类型校验", key, value, validateFieldInfo.getDataType());

        if (ValidationFieldEnum.DATA_NUMBER.getCode().equals(validateFieldInfo.getDataType())){
            if (!ParameterValidator.isNumber(value)){
                log.info("	不为数值，校验失败");
				ParameterValidator.setValidationDetailInfoDTO(key, validateFieldInfo, detail, ValidationFieldEnum.DATA_FORMAT_ERROR);
            }
        }

        if (ValidationFieldEnum.DATA_DATE1.getCode().equals(validateFieldInfo.getDataType())) {
            if (!ParameterValidator.isDate(value, validateFieldInfo.getDataType())) {
                log.info("	非法日期格式[{}]，校验失败", ValidationFieldEnum.DATA_DATE1.getDesc());
				ParameterValidator.setValidationDetailInfoDTO(key, validateFieldInfo, detail, ValidationFieldEnum.DATA_DATE1);
            }
        }

        if (ValidationFieldEnum.DATA_DATE2.getCode().equals(validateFieldInfo.getDataType())) {
            if (!ParameterValidator.isDate(value, validateFieldInfo.getDataType())) {
				log.info("	非法日期格式[{}]，校验失败", ValidationFieldEnum.DATA_DATE2.getDesc());
				ParameterValidator.setValidationDetailInfoDTO(key, validateFieldInfo, detail, ValidationFieldEnum.DATA_DATE2);
            }
        }

        if (ValidationFieldEnum.DATA_DATE3.getCode().equals(validateFieldInfo.getDataType())) {
            if (!ParameterValidator.isDate(value, validateFieldInfo.getDataType())) {
                log.info("	非法日期格式[{}]，校验失败", ValidationFieldEnum.DATA_DATE3.getDesc());
				ParameterValidator.setValidationDetailInfoDTO(key, validateFieldInfo, detail, ValidationFieldEnum.DATA_DATE3);
            }
        }

        if(null != validateFieldInfo.getDataEnum()){
            log.info("	有枚举值，校验判断");
            List<String> dataEnum = Arrays.asList(validateFieldInfo.getDataEnum().split(","));
            if (!dataEnum.contains(value)){
                log.info("	不在枚举值内，校验失败");
				ParameterValidator.setValidationDetailInfoDTO(key, validateFieldInfo, detail, ValidationFieldEnum.DATA_ENUM_ERROR);
            }
        }
    }

	/**
	 * 对字段中的校验器依次校验
	 * @param key
	 * @param medicalData
	 * @param validateFieldInfo
	 * @param details
	 * @return
	 */
	private boolean checkValidators(String key, Map<String, Object> medicalData, ValidateFieldInfo validateFieldInfo, Map<String, ValidateDetailDTO> details) {
		String validatorsCode = validateFieldInfo.getValidatorsCode();
		if (StringUtils.isBlank(validatorsCode)) {
			return true;
		}

		String[] validators = validatorsCode.split(",");
		for (String validatorCode: validators) {
			ValidateValidator standardValidator = validatorService.getValidatorCache(validatorCode);
			if (null == standardValidator) {
				log.info("校验器配置错误，无法找到该校验器[{}]", validatorCode);
				continue;
			}
			String validator = ValidatorTypeEnum.getName(standardValidator.getType());
			if (StringUtils.isBlank(validator)) {
				log.info("无此校验器类型枚举[{}]", validatorCode);
				continue;
			}

			// 开始校验
			Object value = medicalData.get(validateFieldInfo.getFieldCode());
			if (validator.equals(ValidatorTypeEnum.RE_EX.getType())) {
				if( !ParameterValidator.hasValue(value)) {
					// 空值，不校验
					continue;
				}
				if (ParameterValidator.reExCheck((String)value, standardValidator.getRule())) {
					// 满足此正则表达式
					continue;
				}
			}

			if (validator.equals(ValidatorTypeEnum.LOCAL.getType())) {
				if (standardValidator.getCode().equals(ValidatorTypeEnum.LOCAL_ZH.getType())) {
					if( !ParameterValidator.hasValue(value)) {
						// 空值，不校验
						continue;
					}
					if (ParameterValidator.isZh((String)value)) {
						// 是中文
						continue;
					}
				}
				if (standardValidator.getCode().equals(ValidatorTypeEnum.LOCAL_EN_ZH.getType())) {
					if( !ParameterValidator.hasValue(value)) {
						// 空值，不校验
						continue;
					}
					if (ParameterValidator.isEnZh((String)value)) {
						// 是中文英文混合
						continue;
					}
				}
			}

			if (validator.equals(ValidatorTypeEnum.CALCULATE.getType())) {
				if( !ParameterValidator.hasValue(value)) {
					// 空值，不校验
					continue;
				}
				BigDecimal acceptableError = new BigDecimal("0.01");
				BigDecimal result = calculateValidator.calculate(standardValidator.getRule(), validateFieldInfo.getFieldCode(), medicalData);
				if (null == result) {
					// 缺必要值，跳过
					continue;
				}
				BigDecimal currentValue = new BigDecimal((String)value);
				if (currentValue.add(result.negate()).abs().compareTo(acceptableError) == -1) {
					// 差值小于0.01
					continue;
				}
				StringBuilder tips = new StringBuilder(standardValidator.getTips()).append("。计算值为：").
						append(result).append("，传输值为：").append(currentValue);
				ParameterValidator.setValidationDetailInfoDTO(key, validateFieldInfo, details, ValidationFieldEnum.COMPLICATE_FAIL,
						standardValidator.getTips(), tips.toString());
				continue;
			}

			if (validator.equals(ValidatorTypeEnum.LOGICAL_CALCULATE.getType())) {
				Boolean pass = calculateValidator.logicalCalculate(standardValidator.getRule(), validateFieldInfo.getFieldCode(), medicalData);
				if (null == pass || pass) {
					// 缺必要值，跳过
					continue;
				}
			}

			ParameterValidator.setValidationDetailInfoDTOWithExtraReason(key, validateFieldInfo, details, ValidationFieldEnum.COMPLICATE_FAIL,
					standardValidator.getTips());
		}
		return true;
	}
}