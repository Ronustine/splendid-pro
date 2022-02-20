package com.ronustine.splendidpro.common.exception;


/**
 * 类SpServiceException的实现描述：系统内部错误
 * 一个服务一个exception够用
 * @see SpBusinessException
 * @author ronustine
 */
@Deprecated
public class SpServiceException extends RuntimeException{


    private static final long serialVersionUID = 5006036215032887367L;

    public SpServiceException(String msg) {
        super(msg);
    }

    public SpServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}