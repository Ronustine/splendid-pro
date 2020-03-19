package com.ronustine.splendidpro.domain.mapping.service;

import com.ronustine.splendidpro.domain.mapping.ScopeType;
import com.ronustine.splendidpro.domain.mapping.entity.MappingGroup;
import com.ronustine.splendidpro.domain.mapping.entity.MappingRule;
import com.ronustine.splendidpro.domain.mapping.entity.MappingScope;
import com.ronustine.splendidpro.domain.mapping.repository.MappingRuleDao;
import com.ronustine.splendidpro.domain.mapping.repository.MappingRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * rule的
 * 领域层的Service：
 * 有些操作不能放到ENTITY/VALUE OBJECT中，本质是活动或者动作。可以协调多个领域对象。复杂的操作容易搞乱一个简单的对象。
 * 可以说每个方法都是UBIQUITOUS LANGUAGE
 *
 * 作为 Aggregate
 */
@Service
public class MappingRuleService {

    @Autowired
    MappingRuleRepository mappingRuleRepository;

    /**
     * 匹配要的规则
     * @param scopeType
     */
    public MappingRule matchRule(ScopeType scopeType) {
        return null;
    }

    /**
     * 获取规则
     */
    public void getRule() {

    }


    // ************** Factory创建一个rule，这个方法知道rule的内部结构、组装方法  ******************
    /**
     *
     * Factory
     * 处理对象生命周期的开始；
     * @return MappingRule
     */
    public MappingRule buildRule() {
        MappingRule rule = new MappingRule();
        MappingScope scope = new MappingScope();
        MappingGroup group = new MappingGroup();
        rule.setScopes(Collections.singletonList(scope));
        rule.setGroups(Collections.singletonList(group));
        // ... 复杂的装配过程，加上固定规则
        return rule;
    }

    /**
     *
     * Factory
     * 将对象从数据库重建
     * 重建不满足固定规则是要有修复策略的
     * @param ruleId 主键
     * @return MappingRule
     */
    public MappingRule rebuildRule(Long ruleId) {
        return mappingRuleRepository.rule(ruleId);
    }
}
