package com.ronustine.splendidpro.validation.entity.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "validate_field_info")
public class ValidateFieldInfo {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "interface_id")
    private Long interfaceId;

    /**
     * 字段名称
     */
    @Column(name = "field_name")
    private String fieldName;

    /**
     * 字段code

     */
    @Column(name = "field_code")
    private String fieldCode;

    /**
     * 字段描述
     */
    @Column(name = "field_desc")
    private String fieldDesc;

    @Column(name = "data_type")
    private String dataType;

    @Column(name = "data_enum")
    private String dataEnum;

    /**
     * 父节点
     */
    @Column(name = "parent_field")
    private String parentField;

    /**
     * 非空
     */
    @Column(name = "field_level")
    private String fieldLevel;

    /**
     * 是否是父节点
     */
    @Column(name = "is_parent")
    private String isParent;

    /**
     * 层级
     */
    @Column(name = "index_level")
    private Integer indexLevel;

    /**
     * 校验器们
     */
    @Column(name = "validators_code")
    private String validatorsCode;

    /**
     * 创建者 : 创建者
     */
    private String creator;

    /**
     * 创建时间 : 创建时间
     */
    @Column(name = "gmt_created")
    private Date gmtCreated;

    /**
     * 修改者 : 修改者
     */
    private String modifier;

    /**
     * 修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 是否删除 : 是否删除
     */
    @Column(name = "is_deleted")
    private String isDeleted;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return interface_id
     */
    public Long getInterfaceId() {
        return interfaceId;
    }

    /**
     * @param interfaceId
     */
    public void setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
    }

    /**
     * 获取字段名称
     *
     * @return field_name - 字段名称
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * 设置字段名称
     *
     * @param fieldName 字段名称
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * 获取字段code

     *
     * @return field_code - 字段code

     */
    public String getFieldCode() {
        return fieldCode;
    }

    /**
     * 设置字段code

     *
     * @param fieldCode 字段code

     */
    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    /**
     * 获取字段描述
     *
     * @return field_desc - 字段描述
     */
    public String getFieldDesc() {
        return fieldDesc;
    }

    /**
     * 设置字段描述
     *
     * @param fieldDesc 字段描述
     */
    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }

    /**
     * @return data_type
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * @param dataType
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * @return data_enum
     */
    public String getDataEnum() {
        return dataEnum;
    }

    /**
     * @param dataEnum
     */
    public void setDataEnum(String dataEnum) {
        this.dataEnum = dataEnum;
    }

    /**
     * 获取父节点
     *
     * @return parent_field - 父节点
     */
    public String getParentField() {
        return parentField;
    }

    /**
     * 设置父节点
     *
     * @param parentField 父节点
     */
    public void setParentField(String parentField) {
        this.parentField = parentField;
    }

    /**
     * 获取非空
     *
     * @return field_level - 非空
     */
    public String getFieldLevel() {
        return fieldLevel;
    }

    /**
     * 设置非空
     *
     * @param fieldLevel 非空
     */
    public void setFieldLevel(String fieldLevel) {
        this.fieldLevel = fieldLevel;
    }

    /**
     * 获取是否是父节点
     *
     * @return is_parent - 是否是父节点
     */
    public String getIsParent() {
        return isParent;
    }

    /**
     * 设置是否是父节点
     *
     * @param isParent 是否是父节点
     */
    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    /**
     * 获取层级
     *
     * @return index_level - 层级
     */
    public Integer getIndexLevel() {
        return indexLevel;
    }

    /**
     * 设置层级
     *
     * @param indexLevel 层级
     */
    public void setIndexLevel(Integer indexLevel) {
        this.indexLevel = indexLevel;
    }

    /**
     * 获取验收器们
     *
     * @return validators_code - 验收器们
     */
    public String getValidatorsCode() {
        return validatorsCode;
    }

    /**
     * 设置验收器们
     *
     * @param validatorsCode 验收器们
     */
    public void setValidatorsCode(String validatorsCode) {
        this.validatorsCode = validatorsCode;
    }

    /**
     * 获取创建者 : 创建者
     *
     * @return creator - 创建者 : 创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建者 : 创建者
     *
     * @param creator 创建者 : 创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建时间 : 创建时间
     *
     * @return gmt_created - 创建时间 : 创建时间
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 设置创建时间 : 创建时间
     *
     * @param gmtCreated 创建时间 : 创建时间
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * 获取修改者 : 修改者
     *
     * @return modifier - 修改者 : 修改者
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 设置修改者 : 修改者
     *
     * @param modifier 修改者 : 修改者
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * 获取修改时间
     *
     * @return gmt_modified - 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置修改时间
     *
     * @param gmtModified 修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取是否删除 : 是否删除
     *
     * @return is_deleted - 是否删除 : 是否删除
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除 : 是否删除
     *
     * @param isDeleted 是否删除 : 是否删除
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }
}