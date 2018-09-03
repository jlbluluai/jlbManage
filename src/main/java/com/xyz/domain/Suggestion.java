package com.xyz.domain;

import java.io.Serializable;
import java.util.Date;

public class Suggestion implements Serializable {
	private Integer id;

	private Long uid;

	private Byte status;

	private Date leaveTime;

	private String content;

	private static final long serialVersionUID = 1L;

	/* 视图属性 */
	private String beginTime;
	private String endTime;
	private String content_cut;

	/* 关联视图属性 */
	private User user;
	private Message message;
	private Handler handler;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getContent_cut() {
		return content_cut;
	}

	public void setContent_cut(String content_cut) {
		this.content_cut = content_cut;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
	

}