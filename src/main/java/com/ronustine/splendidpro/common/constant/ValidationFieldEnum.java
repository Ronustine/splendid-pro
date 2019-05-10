package com.ronustine.splendidpro.common.constant;

/**
 * @author ronustine
 * 接口字段描述
 */
public enum ValidationFieldEnum {
	/*
	 必填
	  */
	NOT_NULL_1("1", "必填"),
	NULL("2", "可空"),

    /*
     公用错误信息
     */
    NOT_NULL("NOT_NULL", "不允许为空"),
	NOT_FOUND("NOT_FOUND", "节点缺失"),
	DATA_FORMAT_ERROR("DATA_FORMAT_ERROR", "数据格式错误"),
	DATA_ENUM_ERROR("DATA_ENUM_ERROR", "数据不在枚举值内"),
    NOT_NULL_SPECIFY_CONDITION("P5", "指定条件下，不允许为空"),
    COMPLICATE_FAIL("COMPLICATE_FAIL", "校验不通过"),


    /*
     数据格式
      */
	DATA_LIST("List", "List"),
	DATA_OBJECT("Object", "Object"),
	DATA_NUMBER("Number", "BigDecimal"),
	DATA_STRING("String", "String"),
    DATA_DATE1("Date1", "yyyy-MM-dd HH:mm:ss"),
    DATA_DATE2("Date2", "yyyyMMdd"),
    DATA_DATE3("Date3", "yyyy-MM-dd");

    /**
     * 一个字段含多个校验错误原因使用的拼接符号
     */
    public static final String REASON_APPEND ="、";

    /**
     * 字段码连接符号
     */
    public static final String FIELD_CODE_APPEND =".";

    /**
     * ThreadLocal内的数据缓存键
     */
    public static final String VALIDATE_DATA_CACHE = "VALIDATE_DATA_CACHE";

	ValidationFieldEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDesc(String code) {
        ValidationFieldEnum validationFieldEnum = NOT_NULL;
        for (ValidationFieldEnum type : ValidationFieldEnum.values()){
            if (type.code.equals(code)){
                return type.getDesc();
            }
        }
        return validationFieldEnum.getDesc();
    }

}

