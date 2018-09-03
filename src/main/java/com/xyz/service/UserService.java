package com.xyz.service;

import java.util.List;

import com.xyz.base.BaseService;
import com.xyz.domain.User;

public interface UserService extends BaseService<User, Long> {

	List<User> getUsersByIid(User item);
}
