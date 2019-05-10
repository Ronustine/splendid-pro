package com.ronustine.splendidpro.validation.dao;

import com.ronustine.splendidpro.validation.entity.po.ValidateInterface;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ValidateInterfaceDao extends Mapper<ValidateInterface> {

    /**
     * 根据支持业务的code查找支持的接口列表
     * @param standardCode
     * @return
     */
    List<ValidateInterface> getStandardInterface(@Param("standardCode") String standardCode);

}