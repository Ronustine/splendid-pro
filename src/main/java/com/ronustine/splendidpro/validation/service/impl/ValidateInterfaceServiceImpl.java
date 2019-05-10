package com.ronustine.splendidpro.validation.service.impl;

import com.ronustine.splendidpro.utils.BeanUtil;
import com.ronustine.splendidpro.validation.dao.ValidateInterfaceDao;
import com.ronustine.splendidpro.validation.entity.po.ValidateInterface;
import com.ronustine.splendidpro.validation.service.ValidateInterfaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ronustine
 */
@Service
public class ValidateInterfaceServiceImpl implements ValidateInterfaceService {

    Logger log = LoggerFactory.getLogger(ValidateInterfaceServiceImpl.class);

    @Autowired
    private ValidateInterfaceDao validateInterfaceDao;

    @Override
    public List<ValidateInterface> getStandardInterface(String standardCode) {
        return validateInterfaceDao.getStandardInterface(standardCode);
    }

    @Override
    public ValidateInterface getInterface(Long standardId, String code) {
        ValidateInterface validateInterface = new ValidateInterface();
		validateInterface.setStandardId(standardId);
        validateInterface.setInterfaceCode(code);
        return validateInterfaceDao.selectOne(validateInterface);
    }

    @Override
    public List<ValidateInterface> getStandardByStandardId(Long standardId) {
        ValidateInterface validateInterface = new ValidateInterface();
        validateInterface.setStandardId(standardId);
        return validateInterfaceDao.select(validateInterface);
    }
}
