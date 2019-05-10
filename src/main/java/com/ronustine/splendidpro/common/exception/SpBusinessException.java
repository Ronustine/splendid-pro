package com.ronustine.splendidpro.common.exception;


import com.ronustine.splendidpro.common.constant.SpErrorCodeEnum;

/**
 * 类SpBusinessException的实现描述：业务异常
 *
 * @author ronustine
 */
public class SpBusinessException extends BaseException {

    private static final long serialVersionUID = 6380159503855977930L;

    public SpBusinessException(String msg, SpErrorCodeEnum spErrorCodeEnum) {
        super(msg, spErrorCodeEnum);
    }

    public SpBusinessException(SpErrorCodeEnum resultEnum) {
        super(resultEnum.getDesc(),resultEnum);
    }

    public SpBusinessException(String msg, Throwable cause, SpErrorCodeEnum spErrorCodeEnum) {
        super(msg, cause, spErrorCodeEnum);
    }

    public SpBusinessException(String msg, String code) {
        super(msg, code);
    }



    public SpBusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }


}