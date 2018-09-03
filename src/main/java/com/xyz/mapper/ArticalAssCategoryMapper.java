package com.xyz.mapper;

import com.xyz.domain.ArticalAssCategoryKey;

public interface ArticalAssCategoryMapper {
    int deleteByPrimaryKey(ArticalAssCategoryKey key);

    int insert(ArticalAssCategoryKey record);

    int insertSelective(ArticalAssCategoryKey record);
}