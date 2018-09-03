package com.xyz.mapper;

import com.xyz.domain.Version;

public interface VersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Version record);

    int insertSelective(Version record);

    Version selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Version record);

    int updateByPrimaryKeyWithBLOBs(Version record);

    int updateByPrimaryKey(Version record);
}