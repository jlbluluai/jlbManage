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
import com.xyz.domain.Apply;
import com.xyz.domain.Handler;
import com.xyz.domain.Message;
import com.xyz.domain.Suggestion;
import com.xyz.domain.User;
import com.xyz.domain.UserPasschange;
import com.xyz.dto.PagesFeedback;
import com.xyz.dto.StringDto;
import com.xyz.service.ApplyService;
import com.xyz.service.UserPasschangeService;
import com.xyz.service.UserService;
import com.xyz.util.Utils;

@Controller
public class UserManageController {

	// 日志
	private static Logger logger = Logger.getLogger(UserManageController.class);

	// 引入的服务
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("userPasschangeService")
	private UserPasschangeService userPasschangeService;

	@Autowired
	@Qualifier("applyService")
	private ApplyService applyService;

	/* 基本管理 */
	/**
	 * 用户列表按条件分页获取用户
	 * 
	 * @param currentPage
	 * @param limitNums
	 * @return
	 */
	@RequestMapping(value = "/getPageUsers", method = RequestMethod.GET)
	@ResponseBody
	public PagesFeedback getPageUsers(@RequestParam("currentPage") Integer currentPage,
			@RequestParam("limitNums") Integer limitNums, @RequestParam("beginTime") String beginTime,
			@RequestParam("endTime") String endTime, @RequestParam("userId") String userId,
			@RequestParam("username") String username, @RequestParam("nickname") String nickname,
			@RequestParam("email") String email) {
		PagesFeedback feedback = new PagesFeedback();
		logger.info(CommonElement.separator + "用户列表分页获取用户开始" + CommonElement.separator);

		User user = new User();
		if (!"".equals(beginTime)) {
			user.setBeginTime(beginTime);
		}
		if (!"".equals(endTime)) {
			user.setEndTime(endTime);
		}
		if (!"".equals(userId)) {
			user.setId(Long.parseLong(userId));
		}
		if (!"".equals(username)) {
			user.setUsername(username);
		}
		if (!"".equals(nickname)) {
			user.setNickname(nickname);
		}
		if (!"".equals(email)) {
			user.setEmail(email);
		}

		PageInfo<User> pageInfo = userService.getAppointedPageItems(currentPage, limitNums, user);

		List<Object> list = new ArrayList<>();
		for (User u : pageInfo.getList()) {
			list.add(u);
		}

		feedback.setoList(list);
		feedback.setTotalPages(pageInfo.getPages());
		feedback.setTotalNums(pageInfo.getTotal());
		feedback.setCurrentPage(currentPage);
		feedback.setLimitNums(limitNums);

		logger.info(CommonElement.separator + "用户列表分页获取用户结束" + CommonElement.separator);
		return feedback;
	}

	/**
	 * 用户列表查看指定用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getOneUserById", method = RequestMethod.GET)
	@ResponseBody
	public User getTheUserById(@RequestParam("id") Long id) {
		User user = new User();
		logger.info(CommonElement.separator + "用户列表获取单个用户开始" + CommonElement.separator);
		user = userService.getAppointedItem1(id);
		logger.info(CommonElement.separator + "用户列表获取单个用户结束" + CommonElement.separator);
		return user;
	}

	/**
	 * 用户列表按分页获取密码备案
	 * 
	 * @param currentPage
	 * @param limitNums
	 * @return
	 */
	@RequestMapping(value = "/getPagePasschange", method = RequestMethod.GET)
	@ResponseBody
	public PagesFeedback getPagePasschange(@RequestParam("currentPage") Integer currentPage,
			@RequestParam("limitNums") Integer limitNums) {
		PagesFeedback feedback = new PagesFeedback();
		logger.info(CommonElement.separator + "用户列表分页获取密码备案开始" + CommonElement.separator);

		PageInfo<UserPasschange> pageInfo = userPasschangeService.getAppointedPageItems(currentPage, limitNums, null);

		List<Object> list = new ArrayList<>();
		for (UserPasschange u : pageInfo.getList()) {
			list.add(u);
		}

		feedback.setoList(list);
		feedback.setTotalPages(pageInfo.getPages());
		feedback.setTotalNums(pageInfo.getTotal());
		feedback.setCurrentPage(currentPage);
		feedback.setLimitNums(limitNums);

		logger.info(CommonElement.separator + "用户列表分页获取密码备案结束" + CommonElement.separator);
		return feedback;
	}

	/* 博主管理 */
	/**
	 * 申请博主审批按条件分页获取申请
	 * 
	 * @param currentPage
	 * @param limitNums
	 * @return
	 */
	@RequestMapping(value = "/getPageBloggerApplys", method = RequestMethod.GET)
	@ResponseBody
	public PagesFeedback getPageBloggerApplys(@RequestParam("currentPage") Integer currentPage,
			@RequestParam("limitNums") Integer limitNums, @RequestParam("beginTime") String beginTime,
			@RequestParam("endTime") String endTime, @RequestParam("status") String status,
			@RequestParam("isBlogger") String isBlogger) {
		PagesFeedback feedback = new PagesFeedback();
		logger.info(CommonElement.separator + "申请博主审批按条件分页获取申请开始" + CommonElement.separator);

		Apply apply = new Apply();
		if (!"".equals(beginTime)) {
			apply.setBeginTime(beginTime);
		}
		if (!"".equals(endTime)) {
			apply.setEndTime(endTime);
		}
		if (!"100".equals(status)) {
			apply.setStatus((byte) Integer.parseInt(status));
		}
		if (!"".equals(isBlogger)) {
			apply.setIsBlogger((byte) Integer.parseInt(isBlogger));
		}

		PageInfo<Apply> pageInfo = applyService.getAppointedPageItems(currentPage, limitNums, apply);

		List<Object> list = new ArrayList<>();
		for (Apply a : pageInfo.getList()) {
			String temp = null; 
			try { 
				temp = CommonUtils.getAppointedStr(a.getReason(), 1, 10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			a.setContent_cut(temp + "...");
			list.add(a);
		}

		feedback.setoList(list);
		feedback.setTotalPages(pageInfo.getPages());
		feedback.setTotalNums(pageInfo.getTotal());
		feedback.setCurrentPage(currentPage);
		feedback.setLimitNums(limitNums);

		logger.info(CommonElement.separator + "申请博主审批按条件分页获取申请结束" + CommonElement.separator);
		return feedback;
	}

	/**
	 * 处理一个申请
	 * 
	 * @param id
	 * @param status
	 * @param reason
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/handleOneApplyBlogger", method = RequestMethod.POST)
	@ResponseBody
	public StringDto handleOneApplyBlogger(@RequestParam("id") Long id, @RequestParam("status") String status,
			@RequestParam("reason") String reason, HttpSession session) {
		StringDto stringDto = new StringDto();
		logger.info(CommonElement.separator + "处理一个博主申请开始" + CommonElement.separator);

		String message = "";

		Apply a = applyService.getAppointedItem1(id);
		User user = (User) session.getAttribute("user");

		Message msg = new Message();
		Handler handler = new Handler();

		if ("1".equals(status)) {
			msg.setTitle("博主申请审批致信");
			msg.setCreateTime(new Date());
			msg.setPid(user.getId());
			msg.setRid(a.getUid());
			msg.setStatus((byte) 0);
			msg.setContent("您的博主申请已经通过审批，恭喜您成为本站的博主一员，请重新登录解锁权限 <a href='#' onclick='goLogin()'>前往登录</a> 。同时请遵守发博相关条约，祝您使用愉快。");
			
			handler.setId(Utils.createComplexId());
			handler.setHandletime(new Date());
			handler.setModule("博主申请");
			handler.setHid(user.getId());
			handler.setApid(id);
		} else if ("2".equals(status)) {
			msg.setTitle("博主申请审批致信");
			msg.setCreateTime(new Date());
			msg.setPid(user.getId());
			msg.setRid(a.getUid());
			msg.setStatus((byte) 0);
			msg.setContent("非常抱歉，本站因以下理由：" + reason + " 无法批准您成为博主。");
			
			handler.setId(Utils.createComplexId());
			handler.setHandletime(new Date());
			handler.setModule("博主申请");
			handler.setHid(user.getId());
			handler.setApid(id);
			handler.setReason(reason);
		}

		Apply apply = new Apply();

		apply.setId(id);
		apply.setStatus((byte) Integer.parseInt(status));
		apply.setMessage(msg);
		apply.setIsBlogger((byte) 0);
		apply.setUid(a.getUid());
		apply.setHandler(handler);

		if (applyService.changeAppointedItem(apply)) {
			message = "yes";
		} else {
			message = "审批失败";
		}

		stringDto.setMessage(message);

		logger.info(CommonElement.separator + "处理一个博主申请结束" + CommonElement.separator);
		return stringDto;
	}
}
