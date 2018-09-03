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

import com.xyz.common.CommonElement;
import com.xyz.domain.Apply;
import com.xyz.domain.ArticalCategory;
import com.xyz.domain.Handler;
import com.xyz.domain.Message;
import com.xyz.domain.User;
import com.xyz.dto.StringDto;
import com.xyz.service.ApplyService;
import com.xyz.service.ArticalCategoryService;
import com.xyz.util.Utils;

@Controller
public class BlogManageController {

	// 日志
	private static Logger logger = Logger.getLogger(BlogManageController.class);

	// 引入服务
	@Autowired
	@Qualifier("articalCategoryService")
	private ArticalCategoryService articalCategoryService;

	@Autowired
	@Qualifier("applyService")
	private ApplyService applyService;

	/* 分类管理 */
	/**
	 * 获取分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getBlogCategorys", method = RequestMethod.GET)
	@ResponseBody
	public List<ArticalCategory> getBlogCategorys() {
		List<ArticalCategory> list = new ArrayList<>();
		logger.info(CommonElement.separator + "分类管理获取分类开始" + CommonElement.separator);

		list = articalCategoryService.getAllCategorys();

		logger.info(CommonElement.separator + "分类管理获取分类结束" + CommonElement.separator);
		return list;
	}

	/**
	 * 新增一个博客分类
	 * 
	 * @param pid
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/addOneBlogCategory", method = RequestMethod.POST)
	@ResponseBody
	public StringDto addOneBlogCategory(@RequestParam("pid") Integer pid, @RequestParam("name") String name) {
		StringDto stringDto = new StringDto();
		logger.info(CommonElement.separator + "新增一个博客分类开始" + CommonElement.separator);

		ArticalCategory item = new ArticalCategory();

		item.setName(name);
		if (pid != 0) {
			item.setPid(pid);
		}
		item.setCreateTime(new Date());

		String message = "";

		if (articalCategoryService.saveAppointedItem(item)) {
			message = "yes";
		} else {
			message = "添加失败";
		}

		stringDto.setMessage(message);

		logger.info(CommonElement.separator + "新增一个博客分类结束" + CommonElement.separator);
		return stringDto;
	}

	/**
	 * 修改一个博客分类
	 * 
	 * @param pid
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/changeOneBlogCategory", method = RequestMethod.POST)
	@ResponseBody
	public StringDto changeOneBlogCategory(@RequestParam("id") Integer id, @RequestParam("name") String name) {
		StringDto stringDto = new StringDto();
		logger.info(CommonElement.separator + "修改一个博客分类开始" + CommonElement.separator);

		ArticalCategory item = new ArticalCategory();
		item.setId(id);
		item.setName(name);

		String message = "";

		if (articalCategoryService.changeAppointedItem(item)) {
			message = "yes";
		} else {
			message = "修改失败";
		}

		stringDto.setMessage(message);

		logger.info(CommonElement.separator + "修改一个博客分类结束" + CommonElement.separator);
		return stringDto;
	}

	/* 博客申精 */
	/**
	 * 获取一个申请详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getOneApplyFull", method = RequestMethod.GET)
	@ResponseBody
	public Apply getOneApplyFull(@RequestParam("id") Long id) {
		Apply apply = new Apply();
		logger.info(CommonElement.separator + "获取一个申请详情开始" + CommonElement.separator);

		apply = applyService.getOne1(id);

		logger.info(CommonElement.separator + "获取一个申请详情结束" + CommonElement.separator);
		return apply;
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
	@RequestMapping(value = "/handleOneApplyBlog", method = RequestMethod.POST)
	@ResponseBody
	public StringDto handleOneApplyBlog(@RequestParam("id") Long id, @RequestParam("status") String status,
			@RequestParam("reason") String reason, HttpSession session) {
		StringDto stringDto = new StringDto();
		logger.info(CommonElement.separator + "处理一个博客申精开始" + CommonElement.separator);

		String message = "";

		Apply a = applyService.getOne1(id);
		User user = (User) session.getAttribute("user");

		Message msg = new Message();
		
		Handler handler = new Handler();

		if ("1".equals(status)) {
			msg.setTitle("博客申精审批致信");
			msg.setCreateTime(new Date());
			msg.setPid(user.getId());
			msg.setRid(a.getUid());
			msg.setStatus((byte) 0);
			msg.setContent("您的博客：" + a.getArtical().getTitle() + " 申精通过审核，恭喜您。");
		
			handler.setId(Utils.createComplexId());
			handler.setHandletime(new Date());
			handler.setModule("博客申精");
			handler.setHid(user.getId());
			handler.setApid(id);
		} else if ("2".equals(status)) {
			msg.setTitle("博客申精审批致信");
			msg.setCreateTime(new Date());
			msg.setPid(user.getId());
			msg.setRid(a.getUid());
			msg.setStatus((byte) 0);
			msg.setContent("您的博客：" + a.getArtical().getTitle() + " 申精未通过通过审核，理由如下：" + reason + "。");
		
			handler.setId(Utils.createComplexId());
			handler.setHandletime(new Date());
			handler.setModule("博客申精");
			handler.setHid(user.getId());
			handler.setApid(id);
			handler.setReason(reason);
		}

		Apply apply = new Apply();

		apply.setId(id);
		apply.setStatus((byte) Integer.parseInt(status));
		apply.setMessage(msg);
		apply.setIsBlogger((byte) 1);
		apply.setUid(a.getUid());
		apply.setAid(a.getAid());
		apply.setHandler(handler);

		if (applyService.changeAppointedItem(apply)) {
			message = "yes";
		} else {
			message = "审批失败";
		}

		stringDto.setMessage(message);

		logger.info(CommonElement.separator + "处理一个博客申精结束" + CommonElement.separator);
		return stringDto;
	}

}
