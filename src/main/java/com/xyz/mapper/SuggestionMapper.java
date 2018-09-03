package com.xyz.mapper;

import com.xyz.domain.Suggestion;

public interface SuggestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Suggestion record);

    int insertSelective(Suggestion record);

    Suggestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Suggestion record);

    int updateByPrimaryKeyWithBLOBs(Suggestion record);

    int updateByPrimaryKey(Suggestion record);
}