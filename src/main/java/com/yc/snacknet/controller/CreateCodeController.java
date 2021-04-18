package com.yc.snacknet.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建验证
 * Company 源辰信息
 * @author navy
 * @date 2021年3月28日
 * Email haijunzhou@hnit.edu.cn
 */
@WebServlet("/code")
public class CreateCodeController extends HttpServlet{
	private static final long serialVersionUID = -7279029685141724359L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 处理缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		// 生成验证码
		String code = getRandomCode();
		
		// 将生成的验证码存到session以便校验
		request.getSession().setAttribute("vcode", code);
		
		// 创建验证码图片并返回
		BufferedImage image = getCodeImage(code, 110, 38);
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	/**
	 * 创建验证码图片
	 * @param code 要创建的验证码
	 * @param width 宽度 
	 * @param height 高度
	 * @return
	 */
	private BufferedImage getCodeImage(String code, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		// 获取绘制图片的绘制对象
		Graphics g = image.getGraphics();
		
		// 绘制背景色
		g.setColor(getRandomColor(215, 40)); // 设置画笔的颜色
		
		// 绘制
		g.fillRect(0, 0, width, height);
		
		// 绘制干扰线
		int x1, y1, x2, y2;
		Random rd = new Random();
		for (int i = 0; i < 30; i ++) {
			// 设置干扰线的颜色
			g.setColor(getRandomColor(150, 40));
			x1 = rd.nextInt(width);
			y1 = rd.nextInt(height);
			x2 = rd.nextInt(width);
			y2 = rd.nextInt(height);
			
			g.drawLine(x1, y1, x2, y2);
		}
		
		// 设置字体
		g.setFont(new Font("Couriew New", Font.ITALIC, 24));
		
		// 绘制验证码
		char[] codes = code.toCharArray();
		for (int i = 0, len = codes.length; i < len; i ++) {
			g.setColor(getRandomColor(40, 80));
			g.drawString(String.valueOf(codes[i]), 10 + 24 * i, 28);
		}
		
		g.dispose();
		return image;
	}
	
	/**
	 * 获取随机颜色的
	 * @param start
	 * @param bound
	 * @return
	 */
	private Color getRandomColor(int start, int bound) {
		Random rd = new Random();
		int r = start + rd.nextInt(bound);
		int g = start + rd.nextInt(bound);
		int b = start + rd.nextInt(bound);
		return new Color(r, g, b);
	}

	/**
	 * 随机生成验证码
	 * @return
	 */
	private String getRandomCode() {
		Random rd = new Random();
		StringBuffer sbf = new StringBuffer();
		int flag = 0;
		for (int i = 0; i < 4; i ++) {
			flag = rd.nextInt(3);
			switch(flag) {
			case 0: sbf.append(rd.nextInt(10)); break;
			case 1: sbf.append((char)(rd.nextInt(26) + 65)); break;
			case 2: sbf.append((char)(rd.nextInt(26) + 97)); break;
			}
		}
		return sbf.toString();
	}
}
