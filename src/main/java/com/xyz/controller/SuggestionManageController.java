package com.xyz.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xyz.common.CommonElement;
import com.xyz.common.CommonUtils;
import com.xyz.domain.ArticalCategory;
import com.xyz.domain.Handler;
import com.xyz.domain.Message;
import com.xyz.domain.Suggestion;
import com.xyz.domain.User;
import com.xyz.dto.PagesFeedback;
import com.xyz.dto.StringDto;
import com.xyz.service.SuggestionService;
import com.xyz.util.Utils;

@Controller
public class SuggestionManageController {

	// 日志
	private static Logger logger = Logger.getLogger(SuggestionManageController.class);

	// 引入服务
	@Autowired
	@Qualifier("suggestionService")
	private SuggestionService suggestionService;

	/* 意见管理 */
	/**
	 * 意见处理按条件分页获取意见
	 * 
	 * @param currentPage
	 * @param limitNums
	 * @return
	 */
	@RequestMapping(value = "/getPageSuggestions", method = RequestMethod.GET)
	@ResponseBody
	public PagesFeedback getPageSuggestions(@RequestParam("currentPage") Integer currentPage,
			@RequestParam("limitNums") Integer limitNums, @RequestParam("beginTime") String beginTime,
			@RequestParam("endTime") String endTime, @RequestParam("status") String status) {
		PagesFeedback feedback = new PagesFeedback();
		logger.info(CommonElement.separator + "意见处理获取意见开始" + CommonElement.separator);

		Suggestion suggestion = new Suggestion();
		if (!"".equals(beginTime)) {
			suggestion.setBeginTime(beginTime);
		}
		if (!"".equals(endTime)) {
			suggestion.setEndTime(endTime);
		}
		if (!"100".equals(status)) {
			suggestion.setStatus((byte) Integer.parseInt(status));
		}

		PageInfo<Suggestion> pageInfo = suggestionService.getAppointedPageItems(currentPage, limitNums, suggestion);

		List<Object> list = new ArrayList<>();
		for (Suggestion s : pageInfo.getList()) {
			String temp = null;
			try {
				temp = CommonUtils.getAppointedStr(s.getContent(), 1, 10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			s.setContent_cut(temp + "...");
			list.add(s);
		}

		feedback.setoList(list);
		feedback.setTotalPages(pageInfo.getPages());
		feedback.setTotalNums(pageInfo.getTotal());
		feedback.setCurrentPage(currentPage);
		feedback.setLimitNums(limitNums);

		logger.info(CommonElement.separator + "意见处理获取意见结束" + CommonElement.separator);
		return feedback;
	}

	/**
	 * 处理一个意见
	 * 
	 * @param id
	 * @param status
	 * @param reason
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/handleOneSuggestion", method = RequestMethod.POST)
	@ResponseBody
	public StringDto handleOneSuggestion(@RequestParam("id") Integer id, @RequestParam("status") String status,
			@RequestParam("reason") String reason, HttpSession session) {
		StringDto stringDto = new StringDto();
		logger.info(CommonElement.separator + "处理一个意见开始" + CommonElement.separator);

		String message = "";

		Suggestion s = suggestionService.getAppointedItem1(id);
		User user = (User) session.getAttribute("user");

		Suggestion suggestion = new Suggestion();
		Message msg = new Message();

		Handler handler = new Handler();

		if ("1".equals(status)) {
			msg.setTitle("意见接纳致信");
			msg.setCreateTime(new Date());
			msg.setPid(user.getId());
			msg.setRid(s.getUid());
			msg.setStatus((byte) 0);
			msg.setContent("非常感谢您的意见，您的意见：<br/>" + s.getContent() + "<br/>已经被本站采纳");

			handler.setId(Utils.createComplexId());
			handler.setHandletime(new Date());
			handler.setModule("意见处理");
			handler.setHid(user.getId());
			handler.setSgid((long) id);
			handler.setReason(reason);
		} else if ("2".equals(status)) {
			msg.setTitle("意见无奈无法采纳致信");
			msg.setCreateTime(new Date());
			msg.setPid(user.getId());
			msg.setRid(s.getUid());
			msg.setStatus((byte) 0);
			msg.setContent("非常抱歉，您的意见：<br/>" + s.getContent() + "<br/>本站因以下理由：" + reason + "</br>无法采纳");

			handler.setId(Utils.createComplexId());
			handler.setHandletime(new Date());
			handler.setModule("意见处理");
			handler.setHid(user.getId());
			handler.setSgid((long) id);
			handler.setReason(reason);
		}

		suggestion.setId(id);
		suggestion.setStatus((byte) Integer.parseInt(status));
		suggestion.setMessage(msg);
		suggestion.setHandler(handler);

		if (suggestionService.changeAppointedItem(suggestion)) {
			message = "yes";
		} else {
			message = "审批失败";
		}

		stringDto.setMessage(message);

		logger.info(CommonElement.separator + "处理一个意见结束" + CommonElement.separator);
		return stringDto;
	}

}
