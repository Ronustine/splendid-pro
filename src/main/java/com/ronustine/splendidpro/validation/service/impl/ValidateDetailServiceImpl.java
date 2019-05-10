package com.ronustine.splendidpro.validation.service.impl;

import com.ronustine.splendidpro.validation.dao.ValidateDetailDao;
import com.ronustine.splendidpro.validation.entity.po.ValidateDetail;
import com.ronustine.splendidpro.validation.service.ValidateDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * 类ValidateDetailServiceImpl的实现描述：TODO 类实现描述
 *
 * @author ronustine
 */
@Service
public class ValidateDetailServiceImpl implements ValidateDetailService {

    Logger log = LoggerFactory.getLogger(ValidateDetailServiceImpl.class);

    @Autowired
    ValidateDetailDao validateDetailDao;


    @Override
    public ValidateDetail getById(Long id) {
        Example example = new Example(ValidateDetail.class);
        example.createCriteria().andEqualTo("reason", "asdasd");
        return validateDetailDao.selectOneByExample(example);
//        return validateDetailDao.selectByPrimaryKey(id);
    }
}
