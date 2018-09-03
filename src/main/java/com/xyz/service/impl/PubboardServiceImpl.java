package com.xyz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.xyz.domain.Pubboard;
import com.xyz.mapper.PubboardMapper;
import com.xyz.mapper.PubboardMapperP;
import com.xyz.service.PubboardService;

@Service("pubboardService")
@Transactional
public class PubboardServiceImpl implements PubboardService {

	@Autowired
	private PubboardMapper pubboardMapper;

	@Autowired
	private PubboardMapperP pubboardMapperP;

	@Override
	public Pubboard getAppointedItem1(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pubboard getAppointedItem2(Pubboard item) {
		return pubboardMapperP.selectTheNew();
	}

	@Override
	public boolean changeAppointedItem(Pubboard item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveAppointedItem(Pubboard item) {
		return pubboardMapper.insertSelective(item) > 0;
	}

	@Override
	public boolean cutAppointedItem(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PageInfo<Pubboard> getAppointedPageItems(Integer current, Integer limit, Pubboard item) {
		// TODO Auto-generated method stub
		return null;
	}

}
