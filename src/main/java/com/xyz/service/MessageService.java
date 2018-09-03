package com.xyz.service;

import java.util.List;

import com.xyz.base.BaseService;
import com.xyz.domain.Message;

public interface MessageService extends BaseService<Message, Long> {
	
	boolean savePub(List<Message> list);

}
