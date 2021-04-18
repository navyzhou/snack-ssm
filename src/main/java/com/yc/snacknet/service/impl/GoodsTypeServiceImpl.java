package com.yc.snacknet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.snacknet.bean.GoodsType;
import com.yc.snacknet.mapper.IGoodsTypeMapper;
import com.yc.snacknet.service.IGoodsTypeService;
import com.yc.snacknet.util.StringUtil;

/**
 * Company 源辰信息
 * @author navy
 * @date 2021年3月28日
 * Email haijunzhou@hnit.edu.cn
 */
@Service
public class GoodsTypeServiceImpl implements IGoodsTypeService{
	@Autowired
	private IGoodsTypeMapper mapper;

	@Override
	public int add(GoodsType type) {
		if (StringUtil.checkNull(type.getTname())) {
			return -1;
		}
		return mapper.add(type);
	}

	@Override
	public int update(GoodsType type) {
		if (StringUtil.checkNull(type.getTname())) {
			return -1;
		}
		return mapper.update(type);
	}

	@Override
	public List<GoodsType> findAll() {
		return mapper.findAll();
	}

	@Override
	public List<GoodsType> finds() {
		return mapper.finds();
	}
}
