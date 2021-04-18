package com.yc.snacknet.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.snacknet.bean.OrderInfoDTO;
import com.yc.snacknet.mapper.ICartInfoMapper;
import com.yc.snacknet.mapper.IGoodsInfoMapper;
import com.yc.snacknet.mapper.IOrderInfoMapper;
import com.yc.snacknet.mapper.IOrderItemInfoMapper;
import com.yc.snacknet.service.IOrderInfoService;

@Service
public class OrderInfoServiceImpl implements IOrderInfoService {
	@Autowired
	private IOrderInfoMapper orderInfoMapper;
	
	@Autowired
	private IOrderItemInfoMapper orderItemInfoMapper;
	
	@Autowired
	private IGoodsInfoMapper goodsInfoMapper;
	
	@Autowired
	private ICartInfoMapper cartInfoMapper;

	@Override
	public List<OrderInfoDTO> finds(Integer mno){
		if (mno == null) {
			return Collections.emptyList();
		}
		return orderInfoMapper.finds(mno);
	}

	@Transactional
	@Override
	public String add(Map<String, String> map) {
		int result = -1;
		String ano = map.get("ano"); // 收货地址
		String totalPrice = map.get("totalPrice");
		String cnos = map.get("cnos"); // 购物车编号
		String[] cnosArr = cnos.split(",");
		
		String ono = UUID.randomUUID().toString().replace("-", "");
		// 添加订单到订单表
		Map<String, String> orderMap = new HashMap<String, String>();
		orderMap.put("ono", ono);
		orderMap.put("ano", ano);
		orderMap.put("totalPrice", totalPrice);
		result = orderInfoMapper.add(orderMap);
		
		
		// 添加订单详细到订单详细表
		Map<String, Object> itemMap = new HashMap<String, Object>();
		itemMap.put("ono", ono);
		itemMap.put("cnos", cnosArr);
		result = orderItemInfoMapper.add(itemMap);
		
		// 修改商品库存
		result = goodsInfoMapper.updateStore(cnosArr);
		
		// 删除购物车数据
		result = cartInfoMapper.deletes(cnosArr);
		if (result > 0) {
			return ono;
		}
		return null;
	}

	@Override
	public int update(String ono, Integer status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ono", ono);
		map.put("status", status);
		return orderInfoMapper.update(map);
	}
}
