package com.xyz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyz.domain.Apply;
import com.xyz.domain.Artical;
import com.xyz.domain.User;
import com.xyz.mapper.ApplyMapper;
import com.xyz.mapper.ApplyMapperP;
import com.xyz.service.ApplyService;
import com.xyz.service.ArticalService;
import com.xyz.service.HandlerService;
import com.xyz.service.MessageService;
import com.xyz.service.UserService;

@Transactional
@Service("applyService")
public class ApplyServiceImpl implements ApplyService {

	@Autowired
	private ApplyMapper applyMapper;

	@Autowired
	private ApplyMapperP applyMapperP;

	@Autowired
	@Qualifier("messageService")
	private MessageService messageService;

	@Autowired
	@Qualifier("articalService")
	private ArticalService articalService;

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("handlerService")
	private HandlerService handlerService;

	@Override
	public Apply getAppointedItem1(Long id) {
		return applyMapper.selectByPrimaryKey(id);
	}

	@Override
	public Apply getAppointedItem2(Apply item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeAppointedItem(Apply item) {
		messageService.saveAppointedItem(item.getMessage());
		
		handlerService.saveAppointedItem(item.getHandler());

		if (item.getIsBlogger().equals((byte) 0) && item.getStatus().equals((byte) 1)) {
			User user = new User();
			user.setId(item.getUid());
			user.setIid(3);
			userService.changeAppointedItem(user);
		}
		if (item.getIsBlogger().equals((byte) 1) && item.getStatus().equals((byte) 1)) {
			Artical artical = new Artical();
			artical.setId(item.getAid());
			artical.setIsNice((byte) 1);
			articalService.changeAppointedItem(artical);
		}

		return applyMapper.updateByPrimaryKeySelective(item) > 0;
	}

	@Override
	public boolean saveAppointedItem(Apply item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cutAppointedItem(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 分页获取申请
	 */
	@Override
	public PageInfo<Apply> getAppointedPageItems(Integer current, Integer limit, Apply item) {
		PageHelper.startPage(current, limit);
		return new PageInfo<Apply>(applyMapperP.selectPages(item));
	}

	@Override
	public Apply getOne1(Long id) {
		return applyMapperP.selectOne1(id);
	}

}
