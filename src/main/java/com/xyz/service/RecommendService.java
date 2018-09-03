package com.xyz.service;

import java.util.List;

import com.xyz.base.BaseService;
import com.xyz.domain.Recommend;

public interface RecommendService extends BaseService<Recommend, Integer>{

	List<Recommend> getAll();
}
