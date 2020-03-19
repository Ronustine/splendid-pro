package com.ronustine.splendidpro.domain.mapping.entity;

import com.ronustine.splendidpro.domain.mapping.ScopeType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 映射规则适用范围
 */
@Setter
@Getter
@Table(name = "mapping_scopes")
public class MappingScope extends BaseAuditBean {

    /**
     * 所属规则
     */
    @Column(name = "rule_id", nullable = false)
    private Long ruleId;

    /**
     * 应用对象类型
     */
    @Column(name = "scope", nullable = false, updatable = false, length = 1)
    private Integer scope;

    /**
     * 应用对象ID
     */
    @Column(name = "target_id", nullable = false, updatable = false, length = 11)
    private Long targetId;

    /**
     * 应用对象类型名称
     * @return
     */
    public String getScopeName() {
        return ScopeType.getByCode(this.getScope()).getDescription();
    }
}
