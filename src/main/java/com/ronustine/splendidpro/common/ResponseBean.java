package com.ronustine.splendidpro.common;

import com.ronustine.splendidpro.common.constant.SpErrorCodeEnum;
import lombok.Data;

/**
 * @author by ronustine
 */
@Data
public class ResponseBean<T> {

    private Boolean success;
    private String msg;
    private String code;
    private T result;

    private ResponseBean(Builder<T> builder) {
        this.success = builder.success;
        this.msg = builder.msg;
        this.code = builder.code;
        this.result = builder.result;
    }

    public static <T> Builder<T> builder() {
        return new Builder<T>();
    }

    public static class Builder<T> {

        private T result;

        private Boolean success = false;

        private String msg;

        private String  code;

        public Builder<T> result(T result) {
            this.result = result;
            return this;
        }

        public Builder<T> success() {
            this.success = true;
            this.code = SpErrorCodeEnum.REQUEST_SUCCESS_200.getCode();
            this.msg = SpErrorCodeEnum.REQUEST_SUCCESS_200.getDesc();
            return this;
        }

        public Builder<T> success(String msg) {
            this.success = true;
            this.code = SpErrorCodeEnum.REQUEST_SUCCESS_200.getCode();
            this.msg = msg;
            return this;
        }

        public Builder<T> fail(String code, String msg) {
            this.success = false;
            this.code = code;
            this.msg = msg;
            return this;
        }

        public Builder<T> fail(SpErrorCodeEnum errorCodeEnum) {
            this.success = false;
            this.code = errorCodeEnum.getCode();
            this.msg = errorCodeEnum.getDesc();
            return this;
        }

        public Builder<T> fail(ResponseBean responseBean) {
            this.success = false;
            this.code = responseBean.getCode();
            this.msg = responseBean.getMsg();
            return this;
        }

        public ResponseBean<T> build() {
            return new ResponseBean<T>(this);
        }
    }

}
