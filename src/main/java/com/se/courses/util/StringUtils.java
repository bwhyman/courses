package com.se.courses.util;

public class StringUtils {
	
	/**
	 * 判断某个字符串是否为空
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string){
		if (string == null || string.length() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 移除字符串全部空格
	 * @param string
	 * @return
	 */
	public static String trimAllWhitespace(String string){
		return string.replaceAll("\\s{1,}", "");
	}
	
	/**
	 * 
	 * @param path 文件路径
	 * @return 文件扩展名
	 */
	public static String getFileExtension(String path) {
		return path.substring(path.lastIndexOf(".")+1);
	}
}
