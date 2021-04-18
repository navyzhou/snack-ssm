package com.yc.snacknet.fliter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.snacknet.util.SessionKeys;
import com.yc.snacknet.util.StringUtil;

@WebFilter(filterName = "CheckBackLoginFilter", value = "/back/manager/*", initParams = @WebInitParam(name="errorpage", value = "back/index.html"))
public class CheckBackLoginFilter implements Filter{
	private String errorPage = "login.html";
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		String temp = config.getInitParameter("errorpage");
		if (!StringUtil.checkNull(temp)) {
			errorPage = temp;
		}
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 判断用户有没有登录 -> 如果登录了，我们会将登录信息存到session中
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		// 说明没有登录
		if (req.getSession().getAttribute(SessionKeys.CURRENTBACKLOGINACCOUNT) == null) {
			// 结束当前请求
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/";
			out.print("<script>alert('请先登录...');location.href='" + basePath + errorPage + "';</script>");
			out.flush();
			out.close();
		} else { // 如果登录了，则将当前请求传递给下一个过滤器过滤
			chain.doFilter(req, resp);
		}
	}
}
