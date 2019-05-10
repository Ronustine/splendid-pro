package com.ronustine.splendidpro.validation.service;


import com.ronustine.splendidpro.validation.entity.po.ValidateInterface;

import java.util.List;

/**
 * @Description TODO
 * @Author ronustine
 * @Date 2018/7/9/0009
 */
public interface ValidateInterfaceService {

    /**
     * 获取指定code支持业务的接口列表
     * @param standardCode
     * @return
     */
    List<ValidateInterface> getStandardInterface(String standardCode);


    /**
     * 根据code，standardId获取对应的接口，业务上只存在一个
	 * @param standardId
	 * @param code
     * @return
     */
    ValidateInterface getInterface(Long standardId, String code);

    /**
     * standardId下的接口
     * @param standardId 标准业务Id
     * @return
     */
    List<ValidateInterface> getStandardByStandardId(Long standardId);

}
