package com.xyz.mapper;

import java.util.List;

import com.xyz.domain.Apply;

public interface ApplyMapperP {

	List<Apply> selectPages(Apply item);

	Apply selectOne1(Long id);
}