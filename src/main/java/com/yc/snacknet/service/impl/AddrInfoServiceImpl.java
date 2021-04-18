package com.yc.snacknet.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.snacknet.bean.AddrInfo;
import com.yc.snacknet.mapper.IAddrInfoMapper;
import com.yc.snacknet.service.IAddrInfoService;
import com.yc.snacknet.util.StringUtil;

@Service
public class AddrInfoServiceImpl implements IAddrInfoService{
	@Autowired
	private IAddrInfoMapper addrInfoMapper;

	@Override
	public int add(AddrInfo addr) {
		if (StringUtil.checkNull(addr.getName(), addr.getTel())) {
			return -1;
		}
		return addrInfoMapper.add(addr);
	}

	@Override
	public List<AddrInfo> findByMno(Integer mno) {
		if (mno == null) {
			return Collections.emptyList();
		}
		return addrInfoMapper.findByMno(mno);
	}
}
