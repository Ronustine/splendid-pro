package com.ronustine.splendidpro.validation.entity.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "validate_detail")
public class ValidateDetail {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 外键 字段id
     */
    @Column(name = "field_id")
    private Long fieldId;

    /**
     * 是否通过
     */
    private Boolean pass;

    /**
     * 错误原因
     */
    private String reason;

    /**
     * 原因备注
     */
    private String comments;

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
     * 是否删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

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
     * 获取外键 字段id
     *
     * @return field_id - 外键 字段id
     */
    public Long getFieldId() {
        return fieldId;
    }

    /**
     * 设置外键 字段id
     *
     * @param fieldId 外键 字段id
     */
    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * 获取是否通过
     *
     * @return pass - 是否通过
     */
    public Boolean getPass() {
        return pass;
    }

    /**
     * 设置是否通过
     *
     * @param pass 是否通过
     */
    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    /**
     * 获取错误原因
     *
     * @return reason - 错误原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置错误原因
     *
     * @param reason 错误原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 获取原因备注
     *
     * @return comments - 原因备注
     */
    public String getComments() {
        return comments;
    }

    /**
     * 设置原因备注
     *
     * @param comments 原因备注
     */
    public void setComments(String comments) {
        this.comments = comments;
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
     * 获取是否删除
     *
     * @return is_deleted - 是否删除
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除
     *
     * @param isDeleted 是否删除
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}