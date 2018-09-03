package com.xyz.mapper;

import com.xyz.domain.Dynamic;

public interface DynamicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dynamic record);

    int insertSelective(Dynamic record);

    Dynamic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dynamic record);

    int updateByPrimaryKeyWithBLOBs(Dynamic record);

    int updateByPrimaryKey(Dynamic record);
}