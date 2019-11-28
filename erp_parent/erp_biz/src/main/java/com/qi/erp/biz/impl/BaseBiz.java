package com.qi.erp.biz.impl;

import java.util.List;

import com.qi.erp.biz.IBaseBiz;
import com.qi.erp.dao.IBaseDao;

public class BaseBiz<T> implements IBaseBiz<T> {
	
	/** 数据访问层注入 */
	private IBaseDao<T> baseDao;

	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * 查询全部列表
	 */
	public List<T> getList() {
		return baseDao.getList();
	}
	
	/**
	 * 新增部门
	 */
	public void add(T t) {
		baseDao.add(t);
	}
	
	/**
	 *删除部门
	 */
	public void delete(Long uuid) {
		baseDao.delete(uuid);
		
	}
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @return
	 */
	public T get(Long uuid) {
		return (T) baseDao.get(uuid);
	}
	/**
	 * 更新部门数据
	 */
	public void update(T t) {
		baseDao.update(t);
	}
	/**
	 * 分页
	 * @param t1
	 */
	public List<T> getList(T t1, T t2, Object param, int firstResult, int maxResults) {
		return baseDao.getList(t1, t2, param, firstResult, maxResults);
	}

	/**
	 *获取总记录数
	 */
	public long getCount(T t1, T t2, Object param) {
		return baseDao.getCount(t1,t2,param);
	}
}
