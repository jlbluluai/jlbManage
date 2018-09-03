package com.xyz.mapper;

import com.xyz.domain.Collection;

public interface CollectionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);
}