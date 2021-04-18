package com.yc.snacknet.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snacknet.bean.GoodsType;
import com.yc.snacknet.service.IGoodsTypeService;
import com.yc.snacknet.vo.ResultVO;

@RestController
@RequestMapping("/type")
public class GoodsTypeController {
	@Autowired
	private IGoodsTypeService typeService;

	@RequestMapping("/finds")
	public ResultVO finds(){
		List<GoodsType> list = typeService.finds();
		if (list == null || list.isEmpty()) {
			return new ResultVO(300, "暂无数据");
		}
		return new ResultVO(200, "成功", list);
	}

	@RequestMapping("/findAll")
	public List<GoodsType> findAll() {
		 return typeService.findAll();
	}

	@RequestMapping("/update")
	public ResultVO update(GoodsType type) throws IOException {
		if (typeService.update(type) > 0) {
			return new ResultVO(200, "成功");
		}
		return new ResultVO(500, "失败");
	}

	@RequestMapping("/add")
	public ResultVO add(GoodsType type) {
		if (typeService.add(type) > 0) {
			return new ResultVO(200, "成功");
		}
		return new ResultVO(500, "失败");
	}
}
