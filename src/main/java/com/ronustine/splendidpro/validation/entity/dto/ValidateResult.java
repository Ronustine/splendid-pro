package com.ronustine.splendidpro.validation.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * 类ValidateResult的实现描述：校验结果
 *
 * @author ronustine
 */
@Data
public class ValidateResult {

    /**
     * 格式化校验信息
     */
    private List<ValidateDetailDTO> detailInfoDTOList;
    /**
     * 校验信息
     */
    private String validationInfo;
    /**
     * 备注信息
     */
    private String commentInfo;

    private ValidateResult(Builder builder) {
        this.detailInfoDTOList = builder.detailInfoDTOList;
        this.validationInfo = builder.validationInfo;
        this.commentInfo = builder.commentInfo;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<ValidateDetailDTO> detailInfoDTOList;

        private String validationInfo;

        private String commentInfo;

        public Builder detailInfoDTOList(List<ValidateDetailDTO> detailInfoDTOList) {
            this.detailInfoDTOList = detailInfoDTOList;
            return this;
        }

        public Builder validationInfo(String validationInfo) {
            this.validationInfo = validationInfo;
            return this;
        }

        public Builder commentInfo(String commentInfo) {
            this.commentInfo = commentInfo;
            return this;
        }

        public ValidateResult build() {
            return new ValidateResult(this);
        }

    }
}
