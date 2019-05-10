package com.ronustine.splendidpro.validation.service;


import com.ronustine.splendidpro.validation.entity.dto.ValidateFieldInfoDTO;
import com.ronustine.splendidpro.validation.entity.po.ValidateFieldInfo;

import java.util.List;
import java.util.Map;

/**
 * @author ronustine
 */
public interface ValidateFieldInfoService {

    /**
     * 解析并保存json结构，与interfaceId关联
     * @param interfaceId
     * @param json
     * @return
     */
    int analyzeJsonStructure(String interfaceId, String json);

    /**
     * 根据interfaceId接口Id获取对应的字段，（此方法为直接调用数据库，可以移至FieldStructureFactory调用）
     * @param interfaceId
     * @return
     */
    Map<String, ValidateFieldInfo> findMapByInterfaceId(String interfaceId);

    /**
     * 根据interfaceId接口Id获取对应的字段，key为父节点（上一级）
     * @param interfaceId
     * @return
     */
    Map<String, List<ValidateFieldInfo>> findParentWithSubFieldByInterfaceId(String interfaceId);

    /**
     * 根据interfaceId接口Id获取对应的字段
     * @param interfaceId
     * @return
     */
    List<ValidateFieldInfo> findByInterfaceId(String interfaceId);

	/**
	 * 根据interfaceId接口Id获取对应的字段，包含结构信息
	 * @param interfaceId
	 * @return
	 */
	ValidateFieldInfoDTO getStructureByInterfaceId(Long interfaceId);

}
