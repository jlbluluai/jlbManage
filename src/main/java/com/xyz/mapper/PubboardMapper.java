package com.xyz.mapper;

import com.xyz.domain.Pubboard;

public interface PubboardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pubboard record);

    int insertSelective(Pubboard record);

    Pubboard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pubboard record);

    int updateByPrimaryKeyWithBLOBs(Pubboard record);

    int updateByPrimaryKey(Pubboard record);
}