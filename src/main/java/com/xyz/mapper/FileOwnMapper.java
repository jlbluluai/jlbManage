package com.xyz.mapper;

import com.xyz.domain.FileOwn;

public interface FileOwnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FileOwn record);

    int insertSelective(FileOwn record);

    FileOwn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileOwn record);

    int updateByPrimaryKey(FileOwn record);
}