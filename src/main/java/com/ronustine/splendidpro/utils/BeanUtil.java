package com.ronustine.splendidpro.utils;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

/**
 * @Description bean操作相关工具类
 * @Author ronustine
 * @Date 2018/7/5/
 */
public class BeanUtil {

    public static void BeanCopier(Object source , Object target) {
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }

	/**
	 * 带转换器
	 * @param source
	 * @param target
	 * @param converter
	 */
	public static void BeanCopier(Object source , Object target, Converter converter) {
		BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), true);
		copier.copy(source, target, converter);
	}


	static class BaseConverter implements Converter {
		@Override
		public Object convert(Object value, Class target, Object context) {
			if (null == value) {

			}
			return null;
		}
	}



	public static void main(String[] args) {

    }
}
