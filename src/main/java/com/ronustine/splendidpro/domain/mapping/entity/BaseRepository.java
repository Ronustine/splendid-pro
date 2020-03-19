package com.ronustine.splendidpro.domain.mapping.entity;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * 提供ID为Long类型的repository基类
 *
 * @param <T> repository的数据类型
 */
@NoRepositoryBean
public interface BaseRepository<T> {
}
