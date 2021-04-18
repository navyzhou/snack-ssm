package com.yc.snacknet.mapper;

import java.util.List;
import java.util.Map;

import com.yc.snacknet.bean.GoodsInfo;

public interface IGoodsInfoMapper {
	public int add(GoodsInfo gf);
	
	public int update(GoodsInfo gf);
	
	public GoodsInfo findByGid(String gno);
	
	public List<GoodsInfo> findByPage(Map<String, Object> map);
	
	public int total(Map<String, Object> map);
	
	public List<GoodsInfo> finds(Map<String, Object> map);

	public int totals(Map<String, Object> map);
	
	public int updateStore(String[] cnos);
}


