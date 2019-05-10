package com.ronustine.splendidpro.validation.service.impl;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.ronustine.splendidpro.validation.dao.ValidateValidatorDao;
import com.ronustine.splendidpro.validation.entity.po.ValidateValidator;
import com.ronustine.splendidpro.validation.service.ValidateValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 类MdasStandardValidatorServiceImpl的实现描述：TODO 类实现描述
 *
 * @author diaoxin 2019/4/25 11:18
 */
@Service
public class ValidateValidatorServiceImpl implements ValidateValidatorService {

    Logger log = LoggerFactory.getLogger(ValidateValidatorServiceImpl.class);

    @Autowired
    ValidateValidatorDao validatorDao;

    LoadingCache<String, ValidateValidator> validatorCache = Caffeine.newBuilder().
                    // 6min 过期
                    expireAfterWrite(6, TimeUnit.MINUTES).
                    // 5min 刷新一次
                    refreshAfterWrite(5, TimeUnit.MINUTES).
                    maximumSize(100).
                    build(key -> getValidator(key));

    @Override
    public ValidateValidator getValidatorCache(String code) {
        return validatorCache.get(code);
    }

    public ValidateValidator getValidator(String code) {
        ValidateValidator validator = new ValidateValidator();
        validator.setCode(code);
        return validatorDao.selectOne(validator);
    }

    @Override
    public void refreshValidators(String code) {
        validatorCache.refresh(code);
    }
}
