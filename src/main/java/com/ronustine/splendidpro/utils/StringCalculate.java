package com.ronustine.splendidpro.utils;

/**
 * @Description 常用字符串计算
 * @Author ronustine
 */
public class StringCalculate {

	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	public static String firstCapLowerCase(String str) {
		char[] chars = str.toCharArray();
		if (chars[0] >= 'A' && chars[0] <= 'Z') {
			chars[0] += 32;
		}
		return String.valueOf(chars);
	}


	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String firstCapUpperCase(String str) {
		char[] chars = str.toCharArray();
		if (chars[0] >= 'a' && chars[0] <= 'z') {
			chars[0] -= 32;
		}
		return String.valueOf(chars);
	}

}
