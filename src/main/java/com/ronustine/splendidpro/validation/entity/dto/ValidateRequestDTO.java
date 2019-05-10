package com.ronustine.splendidpro.validation.entity.dto;

import lombok.Data;

/**
 * 类ValidateDTO的实现描述 校验请求类
 *
 * @author ronustine
 */
@Data
public class ValidateRequestDTO {

    /**
     * 标准码
     */
    private String standardCode;

    /**
     * 接口码
     */
    private String interfaceCode;

    /**
     * 校验数据
     */
    private Object validateData;


}
