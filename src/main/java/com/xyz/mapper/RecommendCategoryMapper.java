package com.xyz.mapper;

import com.xyz.domain.RecommendCategory;

public interface RecommendCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecommendCategory record);

    int insertSelective(RecommendCategory record);

    RecommendCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecommendCategory record);

    int updateByPrimaryKey(RecommendCategory record);
}