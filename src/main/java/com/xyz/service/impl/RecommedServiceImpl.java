package com.xyz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.xyz.base.RedisDao;
import com.xyz.domain.Recommend;
import com.xyz.mapper.RecommendMapper;
import com.xyz.service.RecommendService;

@Service("recommendService")
@Transactional
public class RecommedServiceImpl implements RecommendService {

	@Autowired
	private RecommendMapper recommendMapper;
	
	@Autowired
	private RedisDao redisDao;

	@Override
	public Recommend getAppointedItem1(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recommend getAppointedItem2(Recommend item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeAppointedItem(Recommend item) {
		redisDao.delete("recommendCategory");
		return recommendMapper.updateByPrimaryKeySelective(item) > 0;
	}

	@Override
	public boolean saveAppointedItem(Recommend item) {
		redisDao.delete("recommendCategory");
		return recommendMapper.insertSelective(item) > 0;
	}

	@Override
	public boolean cutAppointedItem(Integer id) {
		redisDao.delete("recommendCategory");
		return recommendMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public PageInfo<Recommend> getAppointedPageItems(Integer current, Integer limit, Recommend item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recommend> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
