package com.ronustine.splendidpro.domain.mapping.entity;

import com.ronustine.splendidpro.domain.mapping.GroupRelation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * APP属性映射组
 */
@Setter
@Getter
@Entity
@Table(name = "mapping_groups")
public class MappingGroup extends BaseAuditBean {
    /**
     * 所属规则
     */
    @Column(name = "rule_id", nullable = false)
    private Long ruleId;

    /**
     * 优先级
     */
    @Column(name = "priority", nullable = false, length = 2)
    private Integer priority;

    /**
     * 规则下的条件组的关系
     */
    @Column(name = "relation", nullable = false, length = 1)
    private Integer relation;

    /**
     * 规则下的条件组的关系名称
     */
    public String getRelationName() {
        return GroupRelation.getByCode(this.getRelation()).getDescription();
    }
}
