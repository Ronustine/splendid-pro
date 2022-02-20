package com.ronustine.splendidpro.common.exception;

import lombok.Builder;

/**
 * 业务异常枚举
 */
@Builder
public class CustomExceptionHandler {

    CustomException customException;

    /**
     * 格式：SYS(FUNC)
     *
     * @return 标志 拼接
     */
    public final String jointMark() {
        return customException.funcMark().isEmpty() ?
            customException.systemMark() : String.format("%s(%s)", customException.systemMark(), customException.funcMark());
    }

    /**
     * 错误码格式：SYS(FUNC)100001
     *
     * @return 标志 + 码 拼接
     */
    public final String errorCode() {
        return String.format("%s %d", this.jointMark(), customException.getCode());
    }
    // ------- 标志码设计 --------


    /**
     * @return 面向开发的信息
     */
    public final String debugMsg() {
        return customException.getDebugMsg();
    }

    /**
     * @return 面向用户的信息
     */
    public final String readableMsg() {
        return customException.getReadableMsg().isEmpty() ? customException.getDebugMsg() : customException.getReadableMsg();
    }

    /**
     * @return 拼接码+调试信息
     */
    public final String codeDebugMsg() {
        return this.errorCode() + this.debugMsg();
    }
}
