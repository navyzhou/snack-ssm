package com.yc.snacknet.service;

import java.util.List;
import java.util.Map;

import com.yc.snacknet.bean.CartInfo;

public interface ICartInfoService {
	/**
	 * 获取指定会员的购物车信息
	 * @param mno
	 * @return
	 */
	public List<CartInfo> finds(Integer mno);
	
	/**
	 * 获取购物车信息
	 * @param mno
	 * @return 返回购物车列表，包含购物车编号和商品编号
	 */
	public List<Map<String, String>> info(Integer mno);
	
	/**
	 * 更新购物车中商品的数量
	 * @param map： cno、num
	 * @return
	 */
	public int update(Map<String, Object> map);
	
	/**
	 * 添加购物车信息
	 * @param map：gno、num、mno
	 * @return
	 */
	public int add(CartInfo cf);
	
	/**
	 * 根据购物车编号删除购物车数据
	 * @param cno
	 * @return
	 */
	public int delete(String cno);
	
	/**
	 * 根据购物车编号查询商品信息
	 * @param cnos
	 * @return
	 */
	public List<CartInfo> findByCno(String cno);
}
