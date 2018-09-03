package com.xyz.base;

import com.github.pagehelper.PageInfo;

public interface BaseService<T, K> {

	/**
	 * 通用的根据id获取信息
	 * 
	 * @param uid
	 * @return
	 */
	T getAppointedItem1(K id);

	/**
	 * 通用的根据指定条件获取信息
	 * 
	 * @param item
	 * @return
	 */
	T getAppointedItem2(T item);

	/**
	 * 通用的修改一条信息
	 * 
	 * @param item
	 * @return
	 */
	boolean changeAppointedItem(T item);

	/**
	 * 通用的新增一条信息
	 * 
	 * @param item
	 * @return
	 */
	boolean saveAppointedItem(T item);

	/**
	 * 通用的删除一条信息
	 * 
	 * @param id
	 * @return
	 */
	boolean cutAppointedItem(K id);

	/**
	 * 通用的分页获取信息
	 * 
	 * @param current
	 * @param limit
	 * @param item
	 * @return
	 */
	PageInfo<T> getAppointedPageItems(Integer current, Integer limit, T item);
}
