package com.ronustine.splendidpro.domain.mapping;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 条件组关系
 */
@AllArgsConstructor
@Getter
public enum GroupRelation {
    /**
     * 满足其中之一
     */
    ANY(0,"满足其中之一"),
    /**
     * 满足所有条件
     */
    ALL(1,"满足所有条件")
    ;

    private Integer code;
    private String description;

    public static GroupRelation getByCode(Integer code) {
        for (GroupRelation relation: GroupRelation.values()) {
            if (relation.getCode().equals(code)) {
                return relation;
            }
        }
        throw new IllegalArgumentException("枚举类型不存在");
    }
}
