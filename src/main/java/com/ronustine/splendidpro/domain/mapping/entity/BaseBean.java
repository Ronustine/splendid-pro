package com.ronustine.splendidpro.domain.mapping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * 提供统一的状态信息的entity基类
 */
@Setter
@Getter
@MappedSuperclass
public abstract class BaseBean extends BaseAuditBean {
    /**
     * 状态
     */
    @Column(name = "status", nullable = false, length = 1)
    private Status status = Status.ENABLED;

    @Transient
    public boolean isEnabled() {
        return Status.ENABLED == getStatus();
    }

    @Transient
    public boolean isDisabled() {
        return Status.DISABLED == getStatus();
    }
}
