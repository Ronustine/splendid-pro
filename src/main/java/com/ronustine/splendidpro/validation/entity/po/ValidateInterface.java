package com.ronustine.splendidpro.validation.entity.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "validate_interface")
public class ValidateInterface {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标准code
     */
    @Column(name = "standard_id")
    private Long standardId;

    /**
     * 接口名称
     */
    @Column(name = "interface_name")
    private String interfaceName;

    /**
     * 接口code 
     */
    @Column(name = "interface_code")
    private String interfaceCode;

    /**
     * 接口描述
     */
    @Column(name = "interface_desc")
    private String interfaceDesc;

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
     * 修改时间 : 修改时间
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
     * 获取标准code

     *
     * @return standard_id - 标准code

     */
    public Long getStandardId() {
        return standardId;
    }

    /**
     * 设置标准code

     *
     * @param standardId 标准code

     */
    public void setStandardId(Long standardId) {
        this.standardId = standardId;
    }

    /**
     * 获取接口名称
     *
     * @return interface_name - 接口名称
     */
    public String getInterfaceName() {
        return interfaceName;
    }

    /**
     * 设置接口名称
     *
     * @param interfaceName 接口名称
     */
    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    /**
     * 获取接口code 
     *
     * @return interface_code - 接口code 
     */
    public String getInterfaceCode() {
        return interfaceCode;
    }

    /**
     * 设置接口code 
     *
     * @param interfaceCode 接口code 
     */
    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    /**
     * 获取接口描述
     *
     * @return interface_desc - 接口描述
     */
    public String getInterfaceDesc() {
        return interfaceDesc;
    }

    /**
     * 设置接口描述
     *
     * @param interfaceDesc 接口描述
     */
    public void setInterfaceDesc(String interfaceDesc) {
        this.interfaceDesc = interfaceDesc;
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
     * 获取修改时间 : 修改时间
     *
     * @return gmt_modified - 修改时间 : 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置修改时间 : 修改时间
     *
     * @param gmtModified 修改时间 : 修改时间
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