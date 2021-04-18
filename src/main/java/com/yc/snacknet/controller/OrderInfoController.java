package com.yc.snacknet.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snacknet.bean.MemberInfo;
import com.yc.snacknet.bean.OrderInfoDTO;
import com.yc.snacknet.service.IOrderInfoService;
import com.yc.snacknet.util.SessionKeys;
import com.yc.snacknet.vo.ResultVO;

@RestController
@RequestMapping("/order")
public class OrderInfoController {
	@Autowired
	private IOrderInfoService orderInfoService;
	
	@PostMapping("/add")
	public ResultVO add(@RequestParam Map<String, String> map) {
		String ono = orderInfoService.add(map);
		if (ono == null) {
			return new ResultVO(500, "失败");
		}
		return new ResultVO(200, "成功", ono);
	}
	
	@GetMapping("/finds")
	public ResultVO finds(HttpSession session) {
		Object obj = session.getAttribute(SessionKeys.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			return new ResultVO(501, "未登录");
		}
		MemberInfo mf = (MemberInfo) obj;
		List<OrderInfoDTO> list = orderInfoService.finds(mf.getMno());
		if (list == null || list.isEmpty()) {
			return new ResultVO(502, "无数据");
		}
		return new ResultVO(200, "成功", list);
	}
}
