package com.yc.snacknet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.snacknet.bean.MemberInfo;
import com.yc.snacknet.mapper.IMemberInfoMapper;
import com.yc.snacknet.service.IMemberInfoService;
import com.yc.snacknet.util.StringUtil;

@Service
public class MemberInfoServiceImpl implements IMemberInfoService{
	@Autowired
	private IMemberInfoMapper memberInfoMapper;
	
	@Override
	public MemberInfo login(MemberInfo mf) {
		if (StringUtil.checkNull(mf.getRealName(), mf.getPwd())) {
			return null;
		}
		return memberInfoMapper.login(mf);
	}

	@Override
	public int reg(MemberInfo mf) {
		if (StringUtil.checkNull(mf.getRealName(), mf.getPwd(), mf.getEmail())) {
			return -1;
		}
		return memberInfoMapper.reg(mf);
	}
}
