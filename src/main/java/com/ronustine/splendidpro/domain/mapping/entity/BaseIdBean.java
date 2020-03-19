package com.ronustine.splendidpro.domain.mapping.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

/**
 * 提供统一的唯一ID的entity基类
 */
@Setter
@Getter
@MappedSuperclass
public abstract class BaseIdBean implements Persistable<Long> {
    /**
     * 唯一ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 11)
    private Long id;

    /**
     * 判断当前是否为新的entity或者已经持久化
     *
     * @return 是否为新的entity
     */
    @Override
    @Transient
    public boolean isNew() {
        return null == getId();
    }
}
