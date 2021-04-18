package com.yc.snacknet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snacknet.bean.AddrInfo;
import com.yc.snacknet.bean.MemberInfo;
import com.yc.snacknet.service.IAddrInfoService;
import com.yc.snacknet.util.SessionKeys;
import com.yc.snacknet.vo.ResultVO;

@RestController
@RequestMapping("/addr")
public class AddrInfoController {
	@Autowired
	private IAddrInfoService addrInfoService;
	
	@GetMapping("/findByMno")
	public ResultVO findByMno(HttpSession session) {
		Object obj = session.getAttribute(SessionKeys.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			return new ResultVO(501, "未登录");
		}
		MemberInfo mf = (MemberInfo) obj;
		List<AddrInfo> list = addrInfoService.findByMno(mf.getMno());
		if (list == null || list.isEmpty()) {
			return new ResultVO(502, "暂无数据");
		}
		return new ResultVO(200, "成功", list);
	}
}
