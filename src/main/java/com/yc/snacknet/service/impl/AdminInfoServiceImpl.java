package com.yc.snacknet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.snacknet.bean.AdminInfo;
import com.yc.snacknet.mapper.IAdminInfoMapper;
import com.yc.snacknet.service.IAdminInfoService;
import com.yc.snacknet.util.StringUtil;

/**
 * 业务模型层
 * Company 源辰信息
 * @author navy
 * @date 2021年3月28日
 * Email haijunzhou@hnit.edu.cn
 */
@Service
public class AdminInfoServiceImpl implements IAdminInfoService{
	@Autowired
	private IAdminInfoMapper mapper;

	@Override
	public AdminInfo login(AdminInfo af) {
		if (StringUtil.checkNull(af.getAname(), af.getPwd())) {
			return null;
		}
		
		return mapper.login(af);
	}
}
