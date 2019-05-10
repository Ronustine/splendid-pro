package com.ronustine.splendidpro.validation.service;

import com.ronustine.splendidpro.validation.entity.dto.ValidateResult;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 类ValidMedicalDataService的实现描述：数据校验核心
 *
 * @author ronustine
 */
@Component
public interface ValidationFieldService {

	/**
	 * 校验返回参数的合法性，仅有校验功能
	 * @param standardCode 标准Code
	 * @param interfaceCode 接口Code
	 * @param data
	 * @return 处理结果（包含格式与非格式化数据）
	 */
	ValidateResult getVerifyResponseMsg(String standardCode, String interfaceCode, String data);
}