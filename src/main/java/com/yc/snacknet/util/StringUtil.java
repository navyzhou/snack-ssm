package com.yc.snacknet.util;

public class StringUtil {
	/**
	 * 检查给定的参数中是否有空
	 * @param strs
	 * @return
	 */
	public static boolean checkNull(String ... strs) {
		if (strs == null || strs.length <= 0) {
			return true;
		}
		
		for (String str : strs) {
			if (str == null || "".equals(str) || "null".equals(str)) {
				return true;
			}
		}
		
		return false;
	}
}
