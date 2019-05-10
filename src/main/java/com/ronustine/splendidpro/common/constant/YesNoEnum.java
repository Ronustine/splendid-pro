package com.ronustine.splendidpro.common.constant;

/**
 * @author ronustine
 * 错误码
 */
public enum YesNoEnum {

    /*
    布尔值
     */
    YES(1, "是"),

    NO(0, "否");

	YesNoEnum(int code, String desc) {
		this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}

