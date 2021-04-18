package com.yc.snacknet.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.snacknet.bean.CartInfo;
import com.yc.snacknet.mapper.ICartInfoMapper;
import com.yc.snacknet.service.ICartInfoService;
import com.yc.snacknet.util.StringUtil;

@Service
public class CartInfoServiceImpl implements ICartInfoService{
	@Autowired
	private ICartInfoMapper cartInfoMapper;

	@Override
	public List<CartInfo> finds(Integer mno) {
		if (mno == null) {
			return Collections.emptyList();
		}
		return cartInfoMapper.finds(mno);
	}

	@Override
	public List<Map<String, String>> info(Integer mno) {
		if (mno == null) {
			return Collections.emptyList();
		}
		return cartInfoMapper.info(mno);
	}

	@Override
	public int update(Map<String, Object> map) {
		return cartInfoMapper.update(map);
	}

	@Override
	public int add(CartInfo cf) {
		return cartInfoMapper.add(cf);
	}

	@Override
	public int delete(String cno) {
		if (StringUtil.checkNull(cno)) {
			return -1;
		}
		return cartInfoMapper.delete(cno);
	}

	@Override
	public List<CartInfo> findByCno(String cno) {
		if (StringUtil.checkNull(cno)) {
			return Collections.emptyList();
		}
		return cartInfoMapper.findByCno(cno.split(","));
	}
}
