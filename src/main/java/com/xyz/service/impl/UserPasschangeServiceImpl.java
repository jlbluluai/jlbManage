package com.xyz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyz.domain.UserPasschange;
import com.xyz.mapper.UserPasschangeMapperP;
import com.xyz.service.UserPasschangeService;

@Service("userPasschangeService")
@Transactional
public class UserPasschangeServiceImpl implements UserPasschangeService {

	@Autowired
	private UserPasschangeMapperP userPasschangeMapperP;

	@Override
	public UserPasschange getAppointedItem1(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserPasschange getAppointedItem2(UserPasschange item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeAppointedItem(UserPasschange item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveAppointedItem(UserPasschange item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cutAppointedItem(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 分页获取
	 */
	@Override
	public PageInfo<UserPasschange> getAppointedPageItems(Integer current, Integer limit, UserPasschange item) {
		PageHelper.startPage(current, limit);
		return new PageInfo<UserPasschange>(userPasschangeMapperP.selectPages(item));
	}

}
