package com.yc.snacknet.service;

import java.util.List;

import com.yc.snacknet.bean.AddrInfo;

public interface IAddrInfoService {
	/**
	 * 添加收货地址
	 * @param addr
	 * @return
	 */
	public int add(AddrInfo addr);
	
	/**
	 * 根据会员编号查询收货地址
	 * @param mno
	 * @return
	 */
	public List<AddrInfo> findByMno(Integer mno);
}
