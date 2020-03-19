package com.ronustine.splendidpro.domain.mapping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 这是 根
 * 映射规则
 */
@Setter
@Getter
@Table(name = "mapping_rules")
public class MappingRule extends BaseBean {
    /**
     * 规则名称
     */
    @Column(name = "name", nullable = false, updatable = false, length = 11)
    private String name;

    /**
     * 适用对象
     */
    private List<MappingScope> scopes;

    /**
     * 映射组
     */
    private List<MappingGroup> groups;
}
