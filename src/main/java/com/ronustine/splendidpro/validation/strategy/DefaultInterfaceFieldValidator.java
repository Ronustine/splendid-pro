package com.ronustine.splendidpro.validation.strategy;

import com.ronustine.splendidpro.validation.entity.dto.ValidateDetailDTO;
import com.ronustine.splendidpro.validation.entity.po.ValidateFieldInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description 接口字段关系校验，默认使用
 * @author diaoxin
 * @Date 2018/10/10
 */
@Component
public class DefaultInterfaceFieldValidator extends ParameterValidator {

	Logger log = LoggerFactory.getLogger(DefaultInterfaceFieldValidator.class);

	@Override
	public void interfaceCheck(Map<String, Object> json, Map<String, ValidateFieldInfo> validateFieldInfos,
							   Map<String, ValidateDetailDTO> detail) {
		log.info("无需校验字段之间的逻辑关系");
		return;
	}
}
