package com.yc.snacknet.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.yc.snacknet.util.SessionKeys;
import com.yc.snacknet.util.StringUtil;

@WebListener
public class ApplicationListenter implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent sce)  { 

	}

	public void contextInitialized(ServletContextEvent arg)  { 
		String path = arg.getServletContext().getInitParameter("uploadPath");
		if (StringUtil.checkNull(path)) {
			path = "snacknet_pics";
		}
		
		String basePath = arg.getServletContext().getRealPath("/");
		File fl = new File(basePath, path);
		if (!fl.exists()) {
			fl.mkdirs();
		}
		
		SessionKeys.uploadPath = path;
	}
}
