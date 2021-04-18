package com.yc.snacknet.service;

import java.util.List;
import java.util.Map;

import com.yc.snacknet.bean.GoodsInfo;

public interface IGoodsInfoService {
	public int add(GoodsInfo gf);
	
	public int update(GoodsInfo gf);
	
	public GoodsInfo findByGid(String gno);
	
	public Map<String, Object> findByPage(Map<String, Object> map);
	
	public Map<String, Object> findByFirst(Map<String, Object> map);
	
	public List<GoodsInfo> finds(Map<String, Object> map);
}
