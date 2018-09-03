package com.xyz.mapper;

import com.xyz.domain.UserPasschange;

public interface UserPasschangeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPasschange record);

    int insertSelective(UserPasschange record);

    UserPasschange selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPasschange record);

    int updateByPrimaryKey(UserPasschange record);
}