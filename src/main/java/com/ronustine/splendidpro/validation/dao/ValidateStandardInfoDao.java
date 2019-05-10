package com.ronustine.splendidpro.validation.dao;

import com.ronustine.splendidpro.validation.entity.po.ValidateStandardInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ValidateStandardInfoDao extends Mapper<ValidateStandardInfo> {
    /**
     * 获得所有的业务支持列表
     * @return
     */
    List<ValidateStandardInfo> getStandardList();
}