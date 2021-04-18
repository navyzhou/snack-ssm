package com.yc.snacknet.mapper;

import java.util.List;
import java.util.Map;

import com.yc.snacknet.bean.OrderInfoDTO;

public interface IOrderInfoMapper {
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
	public int add(Map<String, String> map);
	
	/**
	 * 修改订单状态
	 * @param ono
	 * @param status
	 * @return
	 */
	public int update(Map<String, Object> map);
}
