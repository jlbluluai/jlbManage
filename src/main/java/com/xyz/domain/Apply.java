package com.xyz.domain;

import java.io.Serializable;
import java.util.Date;

public class Apply implements Serializable {
	private Long id;

	private Long uid;

	private Byte isBlogger;

	private Long aid;

	private Byte status;

	private Date createTime;

	private String reason;

	private static final long serialVersionUID = 1L;

	/* 视图属性 */
	private String beginTime;
	private String endTime;
	private String content_cut;

	/* 关联视图属性 */
	private User user;
	private Artical artical;
	private Message message;
	private Handler handler;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Byte getIsBlogger() {
		return isBlogger;
	}

	public void setIsBlogger(Byte isBlogger) {
		this.isBlogger = isBlogger;
	}

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason == null ? null : reason.trim();
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

	public Artical getArtical() {
		return artical;
	}

	public void setArtical(Artical artical) {
		this.artical = artical;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}