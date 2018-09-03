package com.xyz.mapper;

import com.xyz.domain.FollowKey;

public interface FollowMapper {
    int deleteByPrimaryKey(FollowKey key);

    int insert(FollowKey record);

    int insertSelective(FollowKey record);
}