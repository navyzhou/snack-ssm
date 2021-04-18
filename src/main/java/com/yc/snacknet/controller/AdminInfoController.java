package com.yc.snacknet.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snacknet.bean.AdminInfo;
import com.yc.snacknet.service.IAdminInfoService;
import com.yc.snacknet.util.SessionKeys;
import com.yc.snacknet.vo.ResultVO;

/**
 * 处理后台管理员请求的
 * Company 源辰信息
 * @author navy
 * @date 2021年3月28日
 * Email haijunzhou@hnit.edu.cn
 */
@RestController // @Controller + @ResponseBody
@RequestMapping("/admin")
public class AdminInfoController {
	@Autowired
	private IAdminInfoService adminInfoservice;
	
	@RequestMapping("/login")
	public ResultVO login(AdminInfo af, HttpSession session) {
		AdminInfo adminInfo = adminInfoservice.login(af);
		if (adminInfo == null) {
			return new ResultVO(500, "失败");
		}
		// 如果登录成功，需要将此登录用户信息存到session
		session.setAttribute(SessionKeys.CURRENTBACKLOGINACCOUNT, adminInfo);
		return new ResultVO(200, "成功");
	}

	@RequestMapping("/check")
	public ResultVO check(HttpSession session) throws IOException {
		Object obj = session.getAttribute(SessionKeys.CURRENTBACKLOGINACCOUNT);
		if (obj == null) { // 说明没有登录
			return new ResultVO(200, "未登录");
		}
		return new ResultVO(200, "已登录", obj);
	}
}
