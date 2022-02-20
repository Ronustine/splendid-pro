package com.ronustine.splendidpro.common.exception;


import com.ronustine.splendidpro.common.constant.SpErrorCodeEnum;

/**
 * 类SpBusinessException的实现描述：业务异常
 *
 * @author ronustine
 */
public class SpBusinessException extends BaseException {

    private static final long serialVersionUID = 6380159503855977930L;

    /**
     * @param spErrorCodeEnum 错误码枚举
     */
    public SpBusinessException(SpErrorCodeEnum spErrorCodeEnum) {
        super(CustomExceptionHandler.builder().customException(spErrorCodeEnum).build());
    }
}