package com.ronustine.splendidpro.validation.service;


import com.ronustine.splendidpro.validation.entity.po.ValidateStandardInfo;

import java.util.List;

/**
 * @Description TODO
 * @Author ronustine
 * @Date 2018/7/9/0009
 */
public interface ValidateStandardInfoService {

    List<ValidateStandardInfo> getStandardList();

	ValidateStandardInfo getById(Long id);

	/**
	 * 根据standardCode获取
	 * @param standardCode
	 * @return
	 */
	ValidateStandardInfo getByCode(String standardCode);
}
