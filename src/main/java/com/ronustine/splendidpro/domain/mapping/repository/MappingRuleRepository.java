package com.ronustine.splendidpro.domain.mapping.repository;

import com.ronustine.splendidpro.domain.mapping.entity.MappingGroup;
import com.ronustine.splendidpro.domain.mapping.entity.MappingRule;
import com.ronustine.splendidpro.domain.mapping.entity.MappingScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 只为确实需要直接访问的AGGREGATE根提供REPOSITORY
 * 这REPOSITORY可以随时替换访问的技术，利用解耦优化性能，比如内存、redis、数据库
 */
@Service
public class MappingRuleRepository {

    @Autowired
    MappingRuleDao mappingRuleDao;

    @Autowired
    MappingScopeDao mappingScopeDao;

    @Autowired
    MappingGroupDao mappingGroupDao;

    // 可以有RuleSpecification做条件

    /**
     * 这是从数据库获取rule
     * @param ruleId 主键
     * @return rule
     */
    public MappingRule rule(Long ruleId) {
        MappingRule rule = mappingRuleDao.selectOne(new MappingRule());
        rule.setScopes(scope(ruleId));
        rule.setGroups(group(ruleId));
        return rule;
    }

    private List<MappingScope> scope(Long ruleId) {
        return mappingScopeDao.select(new MappingScope());
    }

    private List<MappingGroup> group(Long ruleId) {
        return mappingGroupDao.select(new MappingGroup());
    }

}
