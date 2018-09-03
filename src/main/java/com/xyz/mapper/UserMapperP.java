package com.xyz.mapper;

import java.util.List;

import com.xyz.domain.User;

public interface UserMapperP {

	User selectByCondition(User user);
	
	List<User> selectByIid(User item);

	List<User> selectPages(User item);
}