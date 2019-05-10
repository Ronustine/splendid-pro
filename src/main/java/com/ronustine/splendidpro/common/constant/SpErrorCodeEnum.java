package com.ronustine.splendidpro.common.constant;

/**
 * @author ronustine
 * 错误码
 */
public enum SpErrorCodeEnum {

    // 错误码集合
    REQUEST_SUCCESS_200("200","请求成功"),

    ERROR_CORE_1000("1000", "系统错误");

	SpErrorCodeEnum(String code, String desc) {
		this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}

