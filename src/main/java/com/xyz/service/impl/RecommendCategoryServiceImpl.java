package com.xyz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.xyz.domain.RecommendCategory;
import com.xyz.mapper.RecommendCategoryMapper;
import com.xyz.mapper.RecommendCategoryMapperP;
import com.xyz.service.RecommendCategoryService;

@Service("recommendCategoryService")
@Transactional
public class RecommendCategoryServiceImpl implements RecommendCategoryService {

	@Autowired
	private RecommendCategoryMapper recommendCategoryMapper;

	@Autowired
	private RecommendCategoryMapperP recommendCategoryMapperP;

	@Override
	public RecommendCategory getAppointedItem1(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecommendCategory getAppointedItem2(RecommendCategory item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeAppointedItem(RecommendCategory item) {
		return recommendCategoryMapper.updateByPrimaryKeySelective(item) > 0;
	}

	@Override
	public boolean saveAppointedItem(RecommendCategory item) {
		return recommendCategoryMapper.insertSelective(item) > 0;
	}

	@Override
	public boolean cutAppointedItem(Integer id) {
		return recommendCategoryMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public PageInfo<RecommendCategory> getAppointedPageItems(Integer current, Integer limit, RecommendCategory item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecommendCategory> getAll() {
		return recommendCategoryMapperP.selectAll();
	}

}
