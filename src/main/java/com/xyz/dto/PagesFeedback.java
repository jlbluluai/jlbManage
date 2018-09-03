package com.xyz.dto;

import java.io.Serializable;
import java.util.List;

public class PagesFeedback implements Serializable {

	private List<Object> oList; // 列表

	private Integer totalPages; // 总页数

	private Long totalNums; // 总记录数

	private Integer limitNums; // 每页限制记录数

	private Integer currentPage; // 当前页数

	public List<Object> getoList() {
		return oList;
	}

	public void setoList(List<Object> oList) {
		this.oList = oList;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalNums() {
		return totalNums;
	}

	public void setTotalNums(Long totalNums) {
		this.totalNums = totalNums;
	}

	public Integer getLimitNums() {
		return limitNums;
	}

	public void setLimitNums(Integer limitNums) {
		this.limitNums = limitNums;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

}
