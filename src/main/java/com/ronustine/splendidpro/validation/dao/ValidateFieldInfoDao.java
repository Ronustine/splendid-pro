package com.ronustine.splendidpro.validation.dao;

import com.ronustine.splendidpro.validation.entity.po.ValidateFieldInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ValidateFieldInfoDao extends Mapper<ValidateFieldInfo> {

    /**
     * 批量插入数据
     * @param fieldRulesList
     * @return
     */
    int insertCodeBatch(List<ValidateFieldInfo> fieldRulesList);

}