package com.ronustine.splendidpro.common.constant;

/**
 * @author ronustine
 * 校验器类型
 */
public enum ValidatorTypeEnum {

    /**
     * 校验器类型
     */
    LOCAL("LOCAL", "本地自定义校验代码"),
    RE_EX("RE_EX", "正则表达式校验"),
    PRE_NULL("PRE_NULL", "必空-前置条件下"),
    PRE_NOT_NULL("PRE_NOT_NULL", "必填-前置条件下"),
    CALCULATE("CALCULATE", "计算值"),
    LOGICAL_CALCULATE("LOGICAL_CALCULATE", "逻辑运算"),

    LOCAL_ZH("LOCAL_ZH", "中文校验"),
    LOCAL_EN_ZH("LOCAL_EN_ZH", "中英文混合");

    ValidatorTypeEnum(String type, String name) {
		this.type = type;
		this.name = name;
    }

    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String getName(String type) {
        for (ValidatorTypeEnum eEnum : ValidatorTypeEnum.values()){
            if (eEnum.type.equals(type)){
                return eEnum.type;
            }
        }
        return null;
    }

}

