package com.xyz.mapper;

import com.xyz.domain.Artical;
import com.xyz.domain.ArticalWithBLOBs;

public interface ArticalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticalWithBLOBs record);

    int insertSelective(ArticalWithBLOBs record);

    ArticalWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Artical item);

    int updateByPrimaryKeyWithBLOBs(ArticalWithBLOBs record);

    int updateByPrimaryKey(Artical record);
}