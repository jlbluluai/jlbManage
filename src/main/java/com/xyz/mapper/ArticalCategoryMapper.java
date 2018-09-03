package com.xyz.mapper;

import com.xyz.domain.ArticalCategory;

public interface ArticalCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticalCategory record);

    int insertSelective(ArticalCategory record);

    ArticalCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticalCategory record);

    int updateByPrimaryKey(ArticalCategory record);
}