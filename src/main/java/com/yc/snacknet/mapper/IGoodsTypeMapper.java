package com.yc.snacknet.mapper;

import java.util.List;

import com.yc.snacknet.bean.GoodsType;

public interface IGoodsTypeMapper {
	public int add(GoodsType type);
	
	public int update(GoodsType type);
	
	public List<GoodsType> findAll();
	
	public List<GoodsType> finds();
}
