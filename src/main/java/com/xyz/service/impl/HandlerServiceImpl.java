package com.xyz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.xyz.domain.Handler;
import com.xyz.mapper.HandlerMapper;
import com.xyz.service.HandlerService;

@Transactional
@Service("handlerService")
public class HandlerServiceImpl implements HandlerService {

	@Autowired
	private HandlerMapper handlerMapper;

	@Override
	public Handler getAppointedItem1(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Handler getAppointedItem2(Handler item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeAppointedItem(Handler item) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 存
	 */
	@Override
	public boolean saveAppointedItem(Handler item) {
		return handlerMapper.insertSelective(item) > 0;
	}

	@Override
	public boolean cutAppointedItem(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PageInfo<Handler> getAppointedPageItems(Integer current, Integer limit, Handler item) {
		// TODO Auto-generated method stub
		return null;
	}

}
