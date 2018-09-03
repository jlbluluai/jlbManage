package com.xyz.mapper;

import com.xyz.domain.CarouselPlate;

public interface CarouselPlateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarouselPlate record);

    int insertSelective(CarouselPlate record);

    CarouselPlate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarouselPlate record);

    int updateByPrimaryKey(CarouselPlate record);
}