package com.xyz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.xyz.domain.UserInfo;
import com.xyz.mapper.UserInfoMapper;
import com.xyz.service.UserInfoService;

@Transactional
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public UserInfo getAppointedItem1(Long id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserInfo getAppointedItem2(UserInfo item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeAppointedItem(UserInfo item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveAppointedItem(UserInfo item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cutAppointedItem(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PageInfo<UserInfo> getAppointedPageItems(Integer current, Integer limit, UserInfo item) {
		// TODO Auto-generated method stub
		return null;
	}

}
