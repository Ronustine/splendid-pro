package com.ronustine.splendidpro.validation.service;

import com.ronustine.splendidpro.validation.entity.dto.ValidateFieldInfoDTO;
import com.ronustine.splendidpro.validation.entity.po.ValidateFieldInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 享元，存放接口结构
 * @author ronustine
 */
@Service
public class FieldStructureFactory {

    Logger log = LoggerFactory.getLogger(FieldStructureFactory.class);

    private final ReentrantLock lock = new ReentrantLock();
	/**
	 * 管理接口字段
 	 */
    private static final Map<String, Map<String, ValidateFieldInfo>> FIELD_STRUCTURE_MAP = new ConcurrentHashMap();
	/**
	 * 管理接口对应的创建时间
	 */
	private static final Map<String, Long> INTERFACE_FIELD_STRUCTURE_TIME = new ConcurrentHashMap();
	/**
	 * 管理接口字段 - 树
	 */
	private static final Map<Long, Future<ValidateFieldInfoDTO>> FILED_TREEMAP = new ConcurrentHashMap();
	/**
	 * 管理接口对应的创建时间 - 树
	 */
	private static final Map<Long, Long> INTERFACE_FIELD_TREE_TIME = new ConcurrentHashMap();
	/**
	 * 缓存时间，单位ms
	 */
	private static final long VALID_TIME = 5 * 60 * 1000;

    @Autowired
	ValidateFieldInfoService validateFieldInfoService;

    /**
     * 获取接口结构
     * @param interfaceId 接口Id
     * @return
     */
    public Map<String, ValidateFieldInfo> getFieldStructure(String interfaceId){
        log.info("获取interfaceId[{}]的字段结构", interfaceId);
        Map<String, ValidateFieldInfo> fieldStructures = getFieldStructureNoSafe(interfaceId);
        if(null != fieldStructures){
            return fieldStructures;
        }

        log.info("未找到interfaceId[{}]的字段结构，或者过期，进入阻塞，获取字段结构", interfaceId);

        lock.lock();
        try {
			fieldStructures = getFieldStructureNoSafe(interfaceId);
			if(null != fieldStructures){
				log.info("获取interfaceId[{}]的字段结构，释放后续线程", interfaceId);
				return fieldStructures;
			}
			Map<String, ValidateFieldInfo> fieldStructuresNew = validateFieldInfoService.findMapByInterfaceId(interfaceId);
            FIELD_STRUCTURE_MAP.put(interfaceId, fieldStructuresNew);
            INTERFACE_FIELD_STRUCTURE_TIME.put(interfaceId, new Long(System.currentTimeMillis()));
			log.info("成功获取interfaceId[{}]的字段结构，释放第一个进入的线程", interfaceId);
			return fieldStructuresNew;
		} finally {
			lock.unlock();
		}
    }

	/**
	 * 获取树形结构-闭锁
	 * @param interfaceId
	 * @return
	 * @throws InterruptedException
	 */
    public ValidateFieldInfoDTO getFieldTree(final Long interfaceId) throws InterruptedException{
		log.info("获取interfaceId[{}]的字段结构-树形结构", interfaceId);
		while (true) {
			Future<ValidateFieldInfoDTO> f = FILED_TREEMAP.get(interfaceId);
			if (f == null) {
				Callable<ValidateFieldInfoDTO> fieldTreeC = new Callable<ValidateFieldInfoDTO>() {
					@Override
					public ValidateFieldInfoDTO call () throws InterruptedException {
						log.info("获取字段树");
						return validateFieldInfoService.getStructureByInterfaceId(interfaceId);
					}
				};
				FutureTask ft = new FutureTask<ValidateFieldInfoDTO>(fieldTreeC);
				f = FILED_TREEMAP.putIfAbsent(interfaceId, ft);
				if (null == f) {
					f = ft;
					ft.run();
				}
			}
			try {
				return f.get();
			} catch (CancellationException e) {
                FILED_TREEMAP.remove(interfaceId, f);
			} catch (ExecutionException e) {
				throw launderThrowable(e);
			}
		}
	}

    /**
	 * 从缓存中获取字段结构
	 * @param interfaceId
	 * @return
	 */
	private Map<String, ValidateFieldInfo> getFieldStructureNoSafe(String interfaceId){
		Map<String, ValidateFieldInfo> fieldStructures;
		fieldStructures = FIELD_STRUCTURE_MAP.get(interfaceId);
		Long createTime = INTERFACE_FIELD_STRUCTURE_TIME.get(interfaceId);
		Long currentTime = new Long(System.currentTimeMillis());
		if (null == createTime ||
				currentTime - createTime > VALID_TIME){
			return null;
		}
		return fieldStructures;
	}

	/**
	 * 检查是否逾期
	 * @param interfaceId
	 * @return
	 */
	private void checkValid(String interfaceId){
		Future<ValidateFieldInfoDTO> f = FILED_TREEMAP.get(interfaceId);
		Long createTime = INTERFACE_FIELD_TREE_TIME.get(interfaceId);
		Long currentTime = new Long(System.currentTimeMillis());
		if (null == createTime ||
				currentTime - createTime > VALID_TIME){
			log.info("字段树缓存过期，重新获取");
            FILED_TREEMAP.remove(interfaceId, f);
		}
	}

	private RuntimeException launderThrowable(Throwable t) {
		if (t instanceof RuntimeException) {
			return (RuntimeException) t;
		} else if (t instanceof Error) {
			throw (Error) t;
		} else {
			throw new IllegalStateException("not chekced", t);
		}
	}
}
