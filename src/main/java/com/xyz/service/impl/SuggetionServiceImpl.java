package com.xyz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyz.domain.Suggestion;
import com.xyz.domain.UserPasschange;
import com.xyz.mapper.SuggestionMapper;
import com.xyz.mapper.SuggestionMapperP;
import com.xyz.service.HandlerService;
import com.xyz.service.MessageService;
import com.xyz.service.SuggestionService;

@Service("suggestionService")
@Transactional
public class SuggetionServiceImpl implements SuggestionService {

	@Autowired
	private SuggestionMapper suggestionMapper;

	@Autowired
	private SuggestionMapperP suggestionMapperP;

	@Autowired
	@Qualifier("messageService")
	private MessageService messageService;

	@Autowired
	@Qualifier("handlerService")
	private HandlerService handlerService;

	@Override
	public Suggestion getAppointedItem1(Integer id) {
		return suggestionMapper.selectByPrimaryKey(id);
	}

	@Override
	public Suggestion getAppointedItem2(Suggestion item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeAppointedItem(Suggestion item) {
		messageService.saveAppointedItem(item.getMessage());

		handlerService.saveAppointedItem(item.getHandler());

		return suggestionMapper.updateByPrimaryKeySelective(item) > 0;
	}

	@Override
	public boolean saveAppointedItem(Suggestion item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cutAppointedItem(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 分页获取意见
	 */
	@Override
	public PageInfo<Suggestion> getAppointedPageItems(Integer current, Integer limit, Suggestion item) {
		PageHelper.startPage(current, limit);
		return new PageInfo<Suggestion>(suggestionMapperP.selectPages(item));
	}

}
