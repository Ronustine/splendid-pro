package com.ronustine.splendidpro.validation.service;


import com.ronustine.splendidpro.validation.entity.po.ValidateValidator;

/**
 * 类MdasStandardValidatorService的实现描述：TODO 类实现描述
 *
 * @author diaoxin 2019/4/25 11:17
 */
public interface ValidateValidatorService {

    /**
     * 从缓存获取校验器
     * @param code
     * @return
     */
    ValidateValidator getValidatorCache(String code);

    /**
     * 重置校验器	 注意：测试环境没有负载均衡，此方法可用
     * @return
     */
    void refreshValidators(String code);

}
