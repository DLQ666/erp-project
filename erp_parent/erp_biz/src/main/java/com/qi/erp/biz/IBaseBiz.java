package com.qi.erp.biz;

import java.util.List;

/**
 * @author dlq96
 *	通用业务层
 * @param <T>
 */
public interface IBaseBiz<T> {
	/**
	 * 查询全部列表接口
	 * @return
	 */
	public List<T> getList();
	
	/**
	 * 新增
	 * @param t
	 */
	public void add(T t);
	
	/**
	 *删除部门
	 */
	public void delete(Long uuid);
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @return
	 */
	public T get(Long uuid);
	
	/**
	 * 分页
	 * @param t1
	 * @return
	 */
	public List<T> getList(T t1,T t2,Object param,int firstResult,int maxResults);
	
	/**
	 * 记录总记录数
	 * @param t1
	 * @return
	 */
	public long getCount(T t1, T t2, Object param);
	
	/**
	 * 更新部门数据
	 */
	public void update(T t);
}
