package com.xyz.mapper;

import com.xyz.domain.Apply;

public interface ApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Apply record);

    int insertSelective(Apply record);

    Apply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKeyWithBLOBs(Apply record);

    int updateByPrimaryKey(Apply record);
}