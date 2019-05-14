package com.ronustine.splendidpro.common.constant;

/**
 * @author ronustine
 * 错误码
 */
public enum YesNoEnum {

    /*
    布尔值
     */
    YES(1, "是", Boolean.TRUE),

    NO(0, "否", Boolean.FALSE);

	YesNoEnum(int code, String desc, Boolean bool) {
		this.code = code;
        this.desc = desc;
        this.bool = bool;
    }

    private int code;
    private String desc;
    private Boolean bool;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public Boolean getBool() {
        return bool;
    }

}

