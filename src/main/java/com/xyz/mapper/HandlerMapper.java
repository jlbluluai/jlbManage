package com.xyz.mapper;

import com.xyz.domain.Handler;

public interface HandlerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Handler record);

    int insertSelective(Handler record);

    Handler selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Handler record);

    int updateByPrimaryKeyWithBLOBs(Handler record);

    int updateByPrimaryKey(Handler record);
}