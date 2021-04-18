package com.yc.snacknet.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snacknet.bean.CartInfo;
import com.yc.snacknet.bean.MemberInfo;
import com.yc.snacknet.service.ICartInfoService;
import com.yc.snacknet.util.SessionKeys;
import com.yc.snacknet.vo.ResultVO;

@RestController
@RequestMapping("/cart")
public class CartInfoController {
	@Autowired
	private ICartInfoService cartInfoService;

	/**
	 * 查询购物车信息
	 * @param session
	 * @return
	 */
	@GetMapping("/finds")
	public ResultVO finds(HttpSession session) {
		Object obj = session.getAttribute(SessionKeys.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			return new ResultVO(501, "未登录");
		}

		MemberInfo mf = (MemberInfo) obj;
		List<CartInfo> list = cartInfoService.finds(mf.getMno());
		if (list == null || list.isEmpty()) {
			return new ResultVO(502, "暂无数据");
		}
		return new ResultVO(200, "成功", list);
	}

	/**
	 * 获取购物车中的商品数量
	 * @return
	 */
	@GetMapping("/info")
	public ResultVO info(HttpSession session) {
		Object obj = session.getAttribute(SessionKeys.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			return new ResultVO(501, "未登录");
		}
		MemberInfo mf = (MemberInfo) obj;
		List<Map<String, String>> list = cartInfoService.info(mf.getMno());
		if (list == null || list.isEmpty()) {
			return new ResultVO(502, "暂无数据");
		}
		return new ResultVO(200, "成功", list);
	}

	/**
	 * 处理购物车商品数量的改变
	 * @param map
	 * @return
	 */
	@PostMapping("/update")
	public ResultVO update(@RequestParam Map<String, Object> map) {
		int result = cartInfoService.update(map);
		if (result > 0) {
			return new ResultVO(200, "成功");
		}
		return new ResultVO(500, "失败");
	}

	/**
	 * 处理购物车商品数量的改变
	 * @param map
	 * @return
	 */
	@PostMapping("/add")
	public ResultVO add(CartInfo cf) {
		// 生成购物车编号
		String cno = UUID.randomUUID().toString().replace("-", "");
		cf.setCno(cno);
		int result = cartInfoService.add(cf);
		if (result > 0) {
			return new ResultVO(200, "成功", cno);
		}
		return new ResultVO(500, "失败");
	}
	
	@PostMapping("/delete")
	public ResultVO delete(String cno) {
		int result = cartInfoService.delete(cno);
		if (result > 0) {
			return new ResultVO(200, "成功", cno);
		}
		return new ResultVO(500, "失败");
	}
	
	@PostMapping("/findByCnos")
	public ResultVO findByCnos(String cnos) {
		List<CartInfo> list = cartInfoService.findByCno(cnos);
		if (list == null || list.isEmpty()) {
			return new ResultVO(501, "暂无数据");
		}
		return new ResultVO(200, "成功", list);
	}
}
