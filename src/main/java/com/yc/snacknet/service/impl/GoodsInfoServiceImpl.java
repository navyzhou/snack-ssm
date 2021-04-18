package com.yc.snacknet.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.snacknet.bean.GoodsInfo;
import com.yc.snacknet.mapper.IGoodsInfoMapper;
import com.yc.snacknet.service.IGoodsInfoService;
import com.yc.snacknet.util.RequestParamUtil;
import com.yc.snacknet.util.StringUtil;

@Service
public class GoodsInfoServiceImpl implements IGoodsInfoService{
	@Autowired
	private IGoodsInfoMapper mapper;

	@Override
	public int add(GoodsInfo gf) {
		if (StringUtil.checkNull(gf.getGname(), gf.getPrice(), gf.getPics())) {
			return -1;
		}
		return mapper.add(gf);
	}

	@Override
	public int update(GoodsInfo gf) {
		if (StringUtil.checkNull(gf.getGname(), gf.getPrice(), gf.getPics())) {
			return -1;
		}
		return mapper.update(gf);
	}

	@Override
	public GoodsInfo findByGid(String gno) {
		if (StringUtil.checkNull(gno)) {
			return null;
		}
		return mapper.findByGid(gno);
	}

	@Override
	public Map<String, Object> findByPage(Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("total", mapper.total(map));
		result.put("rows", mapper.findByPage(RequestParamUtil.changePageParam(map)));
		return result;
	}

	@Override
	public Map<String, Object> findByFirst(Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", mapper.totals(map));
		result.put("rows", mapper.finds(RequestParamUtil.changePageParam(map)));
		return result;
	}

	@Override
	public List<GoodsInfo> finds(Map<String, Object> map) {
		return mapper.finds(RequestParamUtil.changePageParam(map));
	}
}
