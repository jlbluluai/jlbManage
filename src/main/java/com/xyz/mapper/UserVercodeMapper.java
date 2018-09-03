package com.xyz.mapper;

import com.xyz.domain.UserVercode;

public interface UserVercodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserVercode record);

    int insertSelective(UserVercode record);

    UserVercode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserVercode record);

    int updateByPrimaryKey(UserVercode record);
}