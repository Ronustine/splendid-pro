package com.ronustine.splendidpro.domain.mapping;

import com.ronustine.splendidpro.common.exception.SpServiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 映射规则类型
 */
@AllArgsConstructor
@Getter
public enum ScopeType {
    /**
     * 1
     */
    SCOPE_1(1,"1"),
    /**
     * 2
     */
    SCOPE_2(2,"2"),
    /**
     * 3
     */
    SCOPE_3(3,"3")
    ;

    private Integer code;
    private String description;

    public static ScopeType getByCode(Integer code) {
        for (ScopeType type: ScopeType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new SpServiceException("枚举类型不存在");
    }
}
