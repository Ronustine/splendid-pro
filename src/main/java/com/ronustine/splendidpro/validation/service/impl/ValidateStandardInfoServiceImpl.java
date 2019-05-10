package com.ronustine.splendidpro.validation.service.impl;

import com.ronustine.splendidpro.validation.dao.ValidateStandardInfoDao;
import com.ronustine.splendidpro.validation.entity.po.ValidateStandardInfo;
import com.ronustine.splendidpro.validation.service.ValidateStandardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ronustine
 */
@Service
public class ValidateStandardInfoServiceImpl implements ValidateStandardInfoService {

    @Autowired
    private ValidateStandardInfoDao validateStandardInfoDao;

    @Override
    public List<ValidateStandardInfo> getStandardList() {
        return validateStandardInfoDao.getStandardList();
    }

	@Override
	public ValidateStandardInfo getById(Long id) {
		return validateStandardInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public ValidateStandardInfo getByCode(String standardCode) {
		ValidateStandardInfo validateStandardInfo = new ValidateStandardInfo();
		validateStandardInfo.setStandardCode(standardCode);
		return validateStandardInfoDao.selectOne(validateStandardInfo);
	}
}
