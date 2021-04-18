package com.yc.snacknet.service;

import java.util.List;

import com.yc.snacknet.bean.GoodsType;

public interface IGoodsTypeService {
	public int add(GoodsType type);
	
	public int update(GoodsType type);
	
	public List<GoodsType> findAll();
	
	public List<GoodsType> finds();
}
