package com.ronustine.splendidpro.common.exception;


import com.ronustine.splendidpro.common.constant.SpErrorCodeEnum;

/**
 * 类BaseException的实现描述：业务异常
 *
 * @author ronustine
 */
public class BaseException extends RuntimeException{

    private String resultCode;
    private String resultMsg;

    /**
     * @param msg 内部打日志用的错误信息
     * @param spErrorCodeEnum
     */
    public BaseException(String msg, SpErrorCodeEnum spErrorCodeEnum) {
        super(msg);
        this.resultCode = spErrorCodeEnum.getCode();
        this.resultMsg = spErrorCodeEnum.getDesc();
    }

    /**
     * @param msg 内部打日志用的错误信息
     * @param cause
     * @param spErrorCodeEnum
     */
    public BaseException(String msg, Throwable cause, SpErrorCodeEnum spErrorCodeEnum) {
        super(msg, cause);
        this.resultCode = spErrorCodeEnum.getCode();
        this.resultMsg = spErrorCodeEnum.getDesc();
    }

    /**
     * @param msg 内部打日志用的错误信息
     * @param resultCode
     * @param resultMsg 接口返回给外部的信息
     */
    public BaseException(String msg, String resultCode, String resultMsg) {
        super(msg);
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    /**
     * @param msg 内部打日志用的错误信息
     * @param resultCode
     */
    public BaseException(String msg, String resultCode) {
        super(msg);
        this.resultCode = resultCode;
        this.resultMsg = msg;
    }


    /**
     * @param msg 内部打日志用的错误信息
     * @param cause
     * @param resultCode
     * @param resultMsg 接口返回给外部的信息
     */
    public BaseException(String msg, Throwable cause, String resultCode, String resultMsg) {
        super(msg, cause);
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
