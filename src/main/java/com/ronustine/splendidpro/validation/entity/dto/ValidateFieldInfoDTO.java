package com.ronustine.splendidpro.validation.entity.dto;

import com.ronustine.splendidpro.validation.entity.ValidateFieldInfoIterator;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Description 树形结构，组合模式
 * @Author ronustine
 * @Date 2018/7/27/0027
 */
@Data
public class ValidateFieldInfoDTO {

	/**
	 * 主键id
	 */
	private Long id;

	private Long interfaceId;

	/**
	 * 字段名称
	 */
	private String fieldName;

	/**
	 * 字段code

	 */
	private String fieldCode;

	/**
	 * 字段描述
	 */
	private String fieldDesc;

	private String dataType;

	private String dataEnum;

	/**
	 * 父节点
	 */
	private String parentField;

	/**
	 * 非空
	 */
	private String fieldLevel;

	/**
	 * 是否是父节点
	 */
	private String isParent;

	/**
	 * 层级
	 */
	private Integer indexLevel;

	/**
	 * 校验器们
	 */
	private String validatorsCode;

	/**
	 * 创建者 : 创建者
	 */
	private String creator;

	/**
	 * 创建时间 : 创建时间
	 */
	private Date gmtCreated;

	/**
	 * 修改者 : 修改者
	 */
	private String modifier;

	/**
	 * 修改时间
	 */
	private Date gmtModified;

	/**
	 * 是否删除 : 是否删除
	 */
	private String isDeleted;

	private List<ValidateFieldInfoDTO> validateFieldInfoDTOList = new ArrayList<>();

	private ValidateFieldInfoIterator iterator = null;

	public void add(ValidateFieldInfoDTO validateFieldInfoDTO){
		this.validateFieldInfoDTOList.add(validateFieldInfoDTO);
	}

	public void remove(ValidateFieldInfoDTO validateFieldInfoDTO){
		this.validateFieldInfoDTOList.remove(validateFieldInfoDTO);
	}

	public List<ValidateFieldInfoDTO> getValidateFieldInfoDTOList() {
		return validateFieldInfoDTOList;
	}

	public void setValidateFieldInfoDTOList(List<ValidateFieldInfoDTO> validateFieldInfoDTOList) {
		this.validateFieldInfoDTOList = validateFieldInfoDTOList;
	}

	public Iterator<ValidateFieldInfoDTO> createIterator() {
		if ("0".equals(this.isParent)) {
			return null;
		}
//		if (null == iterator) {
		iterator = new ValidateFieldInfoIterator(validateFieldInfoDTOList.iterator());
//		}
		return iterator;
	}
}
