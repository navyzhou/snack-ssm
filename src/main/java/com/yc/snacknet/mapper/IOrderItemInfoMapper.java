package com.yc.snacknet.mapper;

import java.util.Map;

public interface IOrderItemInfoMapper {
	/**
	 * 添加商品信息
	 * @param map： ono、cnos
	 * @return
	 */
	public int add(Map<String, Object> map);
}
