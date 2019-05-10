package com.ronustine.splendidpro.validation.entity.dto;

import com.ronustine.splendidpro.validation.entity.po.ValidateDetail;
import lombok.Data;

import java.util.List;

/**
 * 类ValidationDetailInfoDTO的实现描述：扩展校验信息
 *
 * @author ronustine
 */
@Data
public class ValidateDetailDTO extends ValidateDetail {

    /**
     * 字段Code
     */
    private String fieldCode;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 多个校验码
     */
    private List<String> validateCodeList;
}
