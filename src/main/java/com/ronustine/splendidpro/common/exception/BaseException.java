package com.ronustine.splendidpro.common.exception;

import lombok.Getter;

/**
 * 类BaseException的实现描述：业务异常
 *
 * @author ronustine
 */
@Getter
public class BaseException extends RuntimeException{

    private String resultCode;
    private String resultMsg;

    /**
     * 性能最高
     * 此方法默认没有cause，不记录堆栈信息，适用于明确、清晰知道业务发生错误的情况。
     * @param handler 异常组装
     */
    public BaseException(CustomExceptionHandler handler) {
        this(handler, false);
        this.resultCode = handler.errorCode();
        this.resultMsg = handler.readableMsg();
    }

    /**
     * 包含message, 可指定是否记录异常
     * @param handler 信息
     * @param recordStackTrace 是否记录异常
     */
    public BaseException(CustomExceptionHandler handler, boolean recordStackTrace) {
        super(handler.codeDebugMsg(), null, false, recordStackTrace);
        this.resultCode = handler.errorCode();
        this.resultMsg = handler.readableMsg();
    }

    /**
     * 包含message和cause, 会记录栈异常
     * @param handler 信息
     * @param cause cause by
     */
    public BaseException(CustomExceptionHandler handler, Throwable cause) {
        super(handler.codeDebugMsg(), cause, false, true);
        this.resultCode = handler.errorCode();
        this.resultMsg = handler.readableMsg();
    }

    /**
     * 重写，禁止抓取堆栈信息
     * 原因：业务异常，业务原因写明白即可，无需底层堆栈错误信息
     * @return 自己

     @Override
     public synchronized Throwable fillInStackTrace() {
     return this;
     }*/
}
