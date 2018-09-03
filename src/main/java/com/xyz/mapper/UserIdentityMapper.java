package com.xyz.mapper;

import com.xyz.domain.UserIdentity;

public interface UserIdentityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserIdentity record);

    int insertSelective(UserIdentity record);

    UserIdentity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserIdentity record);

    int updateByPrimaryKey(UserIdentity record);
}