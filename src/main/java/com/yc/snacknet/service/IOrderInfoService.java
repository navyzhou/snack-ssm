package com.yc.snacknet.service;

import java.util.List;
import java.util.Map;

import com.yc.snacknet.bean.OrderInfoDTO;

public interface IOrderInfoService {
	/**
	 * 根据会员编号，获取历史订单
	 * @param mno
	 * @return
	 */
	public List<OrderInfoDTO> finds(Integer mno);
	
	/**
	 * 添加订单
	 * @param cnos
	 * @param totalPrice
	 * @param ano
	 * @return 返回订单编号
	 */
	public String add(Map<String, String> map);
	
	/**
	 * 修改订单状态
	 * @param ono
	 * @param status
	 * @return
	 */
	public int update(String ono, Integer status);
}
