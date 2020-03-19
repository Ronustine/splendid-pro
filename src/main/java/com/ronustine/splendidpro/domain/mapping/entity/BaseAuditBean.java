package com.ronustine.splendidpro.domain.mapping.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 提供统一的审计信息的entity基类
 */
@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditBean extends BaseIdBean {
    /**
     * 最后更新人
     */
    @Column(name = "updated_by", nullable = false, length = 50)
    @LastModifiedBy
    private String updatedBy;

    /**
     * 最后更新时间
     */
    @Column(name = "updated_dt", nullable = false)
    @LastModifiedDate
    private Date updatedTime;

    /**
     * 创建人
     */
    @Column(name = "created_by", nullable = false, updatable = false, length = 50)
    @CreatedBy
    private String createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_dt",
            nullable = false,
            updatable = false)
    @CreatedDate
    private Date createdTime;
}
