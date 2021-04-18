package com.yc.snacknet.mapper;

import com.yc.snacknet.bean.MemberInfo;

/**
 * Company 源辰信息
 * @author navy
 * @date 2021年4月16日
 * Email haijunzhou@hnit.edu.cn
 */
public interface IMemberInfoMapper {
	/**
	 * 会员登录的方法
	 * @param mf
	 * @return
	 */
	public MemberInfo login(MemberInfo mf);
	
	/**
	 * 用户注册的方法
	 * @param mf
	 * @return
	 */
	public int reg(MemberInfo mf);
}
