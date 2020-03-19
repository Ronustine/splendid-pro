package com.ronustine.splendidpro.domain;

import com.ronustine.splendidpro.common.exception.SpServiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态
 */
@AllArgsConstructor
@Getter
public enum Status {
    /**
     * 已禁用
     */
    DISABLED(0),
    /**
     * 启用中
     */
    ENABLED(1)
    ;

    private Integer code;

    public static Status getByCode(Integer code) {
        for (Status status: Status.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new SpServiceException("状态非法");
    }
}
