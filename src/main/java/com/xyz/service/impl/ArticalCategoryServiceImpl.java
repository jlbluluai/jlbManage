package com.xyz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.xyz.base.RedisDao;
import com.xyz.domain.ArticalCategory;
import com.xyz.mapper.ArticalCategoryMapper;
import com.xyz.mapper.ArticalCategoryMapperP;
import com.xyz.service.ArticalCategoryService;

@Transactional
@Service("articalCategoryService")
public class ArticalCategoryServiceImpl implements ArticalCategoryService {

	@Autowired
	private ArticalCategoryMapper articalCategoryMapper;

	@Autowired
	private ArticalCategoryMapperP articalCategoryMapperP;
	
	@Autowired
	private RedisDao redisDao;

	/**
	 * 修改一个博客分类
	 */
	@Override
	public boolean changeAppointedItem(ArticalCategory item) {
		redisDao.delete("articalCategory");
		return articalCategoryMapper.updateByPrimaryKeySelective(item) > 0;
	}

	/**
	 * 新增一个博客分类
	 */
	@Override
	public boolean saveAppointedItem(ArticalCategory item) {
		redisDao.delete("articalCategory");
		return articalCategoryMapper.insertSelective(item) > 0;
	}

	@Override
	public boolean cutAppointedItem(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PageInfo<ArticalCategory> getAppointedPageItems(Integer current, Integer limit, ArticalCategory item) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取所有的文章分类
	 */
	@Override
	public List<ArticalCategory> getAllCategorys() {
		List<ArticalCategory> list = redisDao.get("articalCategory");
		if (list == null) {
			list = articalCategoryMapperP.selectAll();
			redisDao.set("articalCategory", list);
		}
		return list;
	}

	@Override
	public ArticalCategory getAppointedItem1(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticalCategory getAppointedItem2(ArticalCategory item) {
		// TODO Auto-generated method stub
		return null;
	}

}
