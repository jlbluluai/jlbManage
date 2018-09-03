package com.xyz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyz.domain.Artical;
import com.xyz.domain.User;
import com.xyz.domain.UserInfo;
import com.xyz.mapper.UserInfoMapper;
import com.xyz.mapper.UserMapper;
import com.xyz.mapper.UserMapperP;
import com.xyz.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserMapperP userMapperP;

	@Autowired
	private UserInfoMapper userInfoMapper;

	/**
	 * 根据id获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public User getAppointedItem1(Long id) {
		User user = userMapper.selectByPrimaryKey(id);
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
		user.setUserInfo(userInfo);
		return user;
	}

	/**
	 * 按指定信息查询指定用户信息
	 */
	@Override
	public User getAppointedItem2(User item) {
		return userMapperP.selectByCondition(item);
	}

	@Override
	public boolean changeAppointedItem(User item) {
		return userMapper.updateByPrimaryKeySelective(item) > 0;
	}

	@Override
	public boolean saveAppointedItem(User item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cutAppointedItem(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 根据相关条件分页查询
	 */
	@Override
	public PageInfo<User> getAppointedPageItems(Integer current, Integer limit, User item) {
		PageHelper.startPage(current, limit);
		return new PageInfo<User>(userMapperP.selectPages(item));
	}

	@Override
	public List<User> getUsersByIid(User item) {
		return userMapperP.selectByIid(item);
	}

}
