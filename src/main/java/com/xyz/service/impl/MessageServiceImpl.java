package com.xyz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.xyz.domain.Message;
import com.xyz.mapper.MessageMapper;
import com.xyz.mapper.MessageMapperP;
import com.xyz.service.MessageService;

@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messageMapper;

	@Autowired
	private MessageMapperP messageMapperP;

	@Override
	public Message getAppointedItem1(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message getAppointedItem2(Message item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeAppointedItem(Message item) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 插入一条消息
	 */
	@Override
	public boolean saveAppointedItem(Message item) {
		return messageMapper.insertSelective(item) > 0;
	}

	@Override
	public boolean cutAppointedItem(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PageInfo<Message> getAppointedPageItems(Integer current, Integer limit, Message item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean savePub(List<Message> list) {
		return messageMapperP.insertPub(list) > 0;
	}

}
