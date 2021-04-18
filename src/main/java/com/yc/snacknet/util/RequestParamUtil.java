package com.yc.snacknet.util;

import java.util.Map;

public class RequestParamUtil {
	/**
	 * 处理请求中的分页参数
	 * @param map
	 * @return
	 */
	public static Map<String, Object> changePageParam(Map<String, Object> map) {
		int page = Integer.parseInt(String.valueOf(map.get("page")));
		int rows = Integer.parseInt(String.valueOf(map.get("rows")));
		
		map.put("page", (page - 1) * rows);
		map.put("rows", rows);
		return map;
	}
}
