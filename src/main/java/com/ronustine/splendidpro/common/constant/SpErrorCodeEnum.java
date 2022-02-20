package com.ronustine.splendidpro.common.constant;

import com.ronustine.splendidpro.common.exception.CustomException;
import lombok.Getter;

/**
 * @author ronustine
 * 错误码
 */
@Getter
public enum SpErrorCodeEnum implements CustomException {

    // 错误码集合
    REQUEST_SUCCESS_200(200,"请求成功", ""),

    ERROR_CORE_1000(1000, "XXX参数异常", "系统内部错误"),


    ;

	SpErrorCodeEnum(Integer code, String debugMsg, String readableMsg) {
		this.code = code;
        this.debugMsg = debugMsg;
        this.readableMsg = readableMsg;
    }

    private final Integer code;
    private final String debugMsg;
    private final String readableMsg;

    @Override
    public String systemMark() {
        return "SP";
    }
}

