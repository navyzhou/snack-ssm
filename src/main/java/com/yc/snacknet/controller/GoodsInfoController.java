package com.yc.snacknet.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yc.snacknet.bean.GoodsInfo;
import com.yc.snacknet.service.IGoodsInfoService;
import com.yc.snacknet.util.SessionKeys;
import com.yc.snacknet.vo.ResultVO;

@RestController
@RequestMapping("/goods")
public class GoodsInfoController {
	@Autowired
	private IGoodsInfoService goodsInfoService;

	@RequestMapping("/findByGno")
	public ResultVO findByGno(String gno){
		GoodsInfo gf = goodsInfoService.findByGid(gno);
		if (gf == null) {
			return new ResultVO(300, "暂无数据");
		}
		return new ResultVO(200, "成功", gf);
	}

	@RequestMapping("/finds")
	public ResultVO finds(@RequestParam Map<String, Object> map) {
		List<GoodsInfo> list = goodsInfoService.finds(map);
		if (list == null || list.isEmpty()) {
			return new ResultVO(300, "暂无数据");
		}
		return new ResultVO(200, "成功", list);
	}

	@RequestMapping("/findByFirst")
	public ResultVO findByFirst(@RequestParam Map<String, Object> map)  {
		Map<String, Object> result  = goodsInfoService.findByFirst(map);
		if (result == null || result.isEmpty()) {
			return new ResultVO(300, "暂无数据");
		}
		return new ResultVO(200, "成功", result);
	}

	/**
	 * 处理富文本编辑器中的图片上传
	 * @param upload
	 * @param request
	 * @return
	 */
	@RequestMapping("/upload")
	public Map<String, Object> upload(MultipartFile upload, HttpServletRequest request) {
		String basePath = request.getServletContext().getRealPath(""); // 获取项目在服务器中的真实路径
		
		String savePath = "";
		File dest = null;
		Map<String, Object> result = new HashMap<String, Object>();
		if (upload != null && upload.getSize() > 0) { // 说明确实有文件上传
			try {
				savePath = SessionKeys.uploadPath + "/" + new Date().getTime() + "_" + upload.getOriginalFilename();
				dest = new File(new File(basePath).getParentFile(), savePath); // 将上传的文件写到指定的文件
				upload.transferTo(dest); // 将数据写到文件
				
				// 以下必须这样写
				result.put("fileName", upload.getOriginalFilename());
				result.put("url", "../../" + savePath);
				result.put("uploaded", 1);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@RequestMapping("/update")
	public ResultVO update(GoodsInfo gf, MultipartFile[] goods_pics, HttpServletRequest request) {
		int result = goodsInfoService.update(getInfo(gf, goods_pics, request));
		if (result > 0) {
			return new ResultVO(200, "成功");
		}
		return new ResultVO(500, "失败");
	}

	@RequestMapping("/findByPage")
	public ResultVO findByPage(@RequestParam Map<String, Object> map) {
		Map<String, Object> result = goodsInfoService.findByPage(map);
		if (result == null || result.isEmpty()) {
			return new ResultVO(300, "暂无数据");
		}
		return new ResultVO(200, "成功", result);
	}
	
	/**
	 * 处理商品信息的方法
	 * @param gf
	 * @param goods_pics
	 * @param request
	 * @return
	 */
	private GoodsInfo getInfo(GoodsInfo gf, MultipartFile[] goods_pics, HttpServletRequest request) {
		String basePath = request.getServletContext().getRealPath(""); // 获取项目在服务器中的真实路径

		String savePath = "";
		File dest = null;
		
		if (goods_pics != null && goods_pics.length > 0 && goods_pics[0].getSize() > 0) {
			String picStr = "";
			try {
				for (MultipartFile pic : goods_pics) {
					savePath = SessionKeys.uploadPath + "/" + new Date().getTime() + "_" + pic.getOriginalFilename();
					dest = new File(new File(basePath).getParentFile(), savePath);
					pic.transferTo(dest);
					if ("".equals(picStr)) {
						picStr += "../" + savePath;
					} else {
						picStr += ",../" + savePath;
					}
				}
				gf.setPics(picStr);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return gf;
	}


	@RequestMapping("/add")
	public ResultVO add(GoodsInfo gf, MultipartFile[] goods_pics, HttpServletRequest request)  {
		
		int result = goodsInfoService.add(getInfo(gf, goods_pics, request));
		if (result > 0) {
			return new ResultVO(200, "成功");
		}
		return new ResultVO(500, "失败");
	}
}
