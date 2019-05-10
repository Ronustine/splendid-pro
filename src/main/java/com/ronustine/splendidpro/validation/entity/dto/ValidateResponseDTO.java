package com.ronustine.splendidpro.validation.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * 类ValidateResponseDTO的实现描述 校验响应类
 *
 * @author ronustine
 */
@Data
public class ValidateResponseDTO {

    /**
     * 细节列表
     */
    private List<ValidateDetail> detailList;

    /**
     * 是否校验通过
     */
    private Boolean pass;

    public ValidateResponseDTO(List<ValidateDetail> detailList){
        this.detailList = detailList;
        pass = false;
        if (0 == detailList.size()) {
            pass = true;
        }
    }

    @Data
    public class ValidateDetail {

        private String fieldCode;

        private String fieldName;

        private List<String> validateCodeList;

        private String validateMsg;

        private String commonInfo;
    }
}
