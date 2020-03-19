package com.ronustine.splendidpro.service;

import com.ronustine.splendidpro.domain.mapping.ScopeType;
import com.ronustine.splendidpro.domain.mapping.service.MappingRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 这是应用层
 * 1. 调用领域层
 * 2. 可以与其他应用做交互
 */
@Service
public class RuleService {

    @Autowired
    MappingRuleService mappingRuleService;


    /**
     * 匹配规则
     */
    public void matchRule() {
        mappingRuleService.matchRule(ScopeType.SCOPE_1);
    }

    /**
     * 获取规则
     */
    public void rule() {
        mappingRuleService.getRule();
    }
}
