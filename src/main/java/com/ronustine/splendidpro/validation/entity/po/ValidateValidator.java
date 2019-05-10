package com.ronustine.splendidpro.validation.entity.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "validate_validator")
public class ValidateValidator {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 校验器码
     */
    private String code;

    /**
     * 校验器类型
     */
    private String type;

    /**
     * 校验器名称
     */
    private String name;

    /**
     * 校验器说明
     */
    @Column(name = "v_desc")
    private String vDesc;

    /**
     * 规则
     */
    private String rule;

    /**
     * 校验提示
     */
    private String tips;

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
     * 获取校验器码
     *
     * @return code - 校验器码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置校验器码
     *
     * @param code 校验器码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取校验器类型
     *
     * @return type - 校验器类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置校验器类型
     *
     * @param type 校验器类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取校验器名称
     *
     * @return name - 校验器名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置校验器名称
     *
     * @param name 校验器名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取校验器说明
     *
     * @return v_desc - 校验器说明
     */
    public String getvDesc() {
        return vDesc;
    }

    /**
     * 设置校验器说明
     *
     * @param vDesc 校验器说明
     */
    public void setvDesc(String vDesc) {
        this.vDesc = vDesc;
    }

    /**
     * 获取规则
     *
     * @return rule - 规则
     */
    public String getRule() {
        return rule;
    }

    /**
     * 设置规则
     *
     * @param rule 规则
     */
    public void setRule(String rule) {
        this.rule = rule;
    }

    /**
     * 获取校验提示
     *
     * @return tips - 校验提示
     */
    public String getTips() {
        return tips;
    }

    /**
     * 设置校验提示
     *
     * @param tips 校验提示
     */
    public void setTips(String tips) {
        this.tips = tips;
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