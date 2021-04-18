package com.yc.snacknet.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snacknet.bean.MemberInfo;
import com.yc.snacknet.service.IMemberInfoService;
import com.yc.snacknet.util.SessionKeys;
import com.yc.snacknet.vo.ResultVO;

@RestController
@RequestMapping("/member")
public class MemberInfoController {
	@Autowired
	private IMemberInfoService memberInfoService;

	@PostMapping("/login")
	public ResultVO login(MemberInfo mf, HttpSession session) {
		Object code = session.getAttribute("vcode");
		if (!mf.getRealName().equalsIgnoreCase(String.valueOf(code))) {
			return new ResultVO(501, "验证码错误");
		}
		
		MemberInfo memberInfo = memberInfoService.login(mf);
		if (memberInfo == null) {
			return new ResultVO(500, "失败");
		} 
		session.setAttribute(SessionKeys.CURRENTMEMBERACCOUNT, memberInfo);
		return new ResultVO(200, "成功");
	}
	
	@PostMapping("/reg")
	public ResultVO reg(MemberInfo mf) {
		int result = memberInfoService.reg(mf);
		if (result <= 0) {
			return new ResultVO(500, "失败");
		} 
		return new ResultVO(200, "成功");
	}
	
	@GetMapping("/check")
	public ResultVO check(HttpSession session) {
		Object obj = session.getAttribute(SessionKeys.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			return new ResultVO(500, "未登录");
		}
		return new ResultVO(200, "成功", obj);
	}
}
