package com.qi.erp.dao;

import java.util.List;

/**
 * @author dlq96 抽取
 *  部门数据访问接口
 */
public interface IBaseDao<T> {
	/**
	 * 查询部门全部列表
	 * 
	 * @return
	 */

	public List<T> getList();

	/**
	 * 条件查询
	 * 
	 * @param t1
	 */
	public List<T> getList(T t1, T t2, Object param, int firstResult, int maxResults);

	/**
	 * 计算条件查询的总记录数
	 * 
	 * @param t1
	 * @return
	 */
	public long getCount(T t1, T t2, Object param);

	/**
	 * 新增部门
	 * 
	 * @param t
	 */
	public void add(T t);

	/**
	 * 删除部门
	 * 
	 * @param uuid
	 */
	public void delete(Long uuid);

	/**
	 * 通过编号查询对象
	 * 
	 * @param uuid
	 * @return
	 */
	public T get(Long uuid);

	/**
	 * 更新部门数据
	 */
	public void update(T t);

}
