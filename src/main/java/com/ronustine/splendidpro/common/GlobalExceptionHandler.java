package com.ronustine.splendidpro.common;

import com.ronustine.splendidpro.common.constant.SpErrorCodeEnum;
import com.ronustine.splendidpro.common.exception.BaseException;
import com.ronustine.splendidpro.common.exception.SpBusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类GlobalExceptionHandler的实现描述：统一异常处理
 *
 * @author ronustine
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 未知异常
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseBean handleException(Exception e) {
        log.error("系统内部错误，响应系统内部错误，具体错误见下", e);
        return ResponseBean.builder().fail(SpErrorCodeEnum.ERROR_CORE_1000).build();
    }

    /**
     * 业务异常
     * @return
     */
    @ExceptionHandler(SpBusinessException.class)
    @ResponseBody
    ResponseBean handleBusinessException(BaseException e) {
        log.error("系统业务错误，响应错误内容：[{}]", e.getResultMsg());
        return ResponseBean.builder().fail(e.getResultCode(), e.getResultMsg()).build();
    }

}
