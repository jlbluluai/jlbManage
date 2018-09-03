package com.xyz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.xyz.domain.Version;
import com.xyz.mapper.VersionMapper;
import com.xyz.mapper.VersionMapperP;
import com.xyz.service.VersionService;

@Service("versionService")
@Transactional
public class VersionServiceImpl implements VersionService {

	@Autowired
	private VersionMapper versionMapper;

	@Autowired
	private VersionMapperP versionMapperP;

	@Override
	public Version getAppointedItem1(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Version getAppointedItem2(Version item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeAppointedItem(Version item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveAppointedItem(Version item) {
		return versionMapper.insertSelective(item) > 0;
	}

	@Override
	public boolean cutAppointedItem(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PageInfo<Version> getAppointedPageItems(Integer current, Integer limit, Version item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Version> getAll() {
		return versionMapperP.selectAll();
	}

}
