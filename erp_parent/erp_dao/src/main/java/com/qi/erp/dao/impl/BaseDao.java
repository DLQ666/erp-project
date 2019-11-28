package com.qi.erp.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import com.qi.erp.dao.IBaseDao;

public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {
	
	//定义entityClas属性
	private Class<T> entityClass;
	//添加无参数构造方法，在构造方法里实现entityClass的具体类型
	@SuppressWarnings("unchecked")
	public BaseDao() {
		//this.getClass();
		//通过子类来获取父亲
		Type baseDaoClass = getClass().getGenericSuperclass();
		//转成参数化的类型
		ParameterizedType pType = (ParameterizedType)baseDaoClass;
		//获取参数类型的数组
		Type[] types = pType.getActualTypeArguments();
		//得到了泛型里的T的类型
		Type targrtType = types[0];
		//转成class类型
		entityClass = (Class<T>)targrtType;
	}
	
	/**
	 * 查询所有部门信息
	 */
	@SuppressWarnings("unchecked")
	public List<T> getList() {
		return (List<T>) this.getHibernateTemplate().find("from T");
	}

	/**
	 * 条件查询
	 */
	@SuppressWarnings("unchecked")
	public List<T> getList(T t1, T t2, Object param, int firstResult, int maxResults) {
		DetachedCriteria dc = getDetachedCriteria(t1, t2, param);
		return (List<T>) getHibernateTemplate().findByCriteria(dc, firstResult, maxResults);
	}

	/**
	 * 新增部门
	 */
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	/**
	 * 删除部门
	 */
	public void delete(Long uuid) {
		// 让对象进入持久化状态
		T t = this.getHibernateTemplate().get(entityClass, uuid);
		// 删除持久化状态
		this.getHibernateTemplate().delete(t);
	}

	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @return
	 */
	public T get(Long uuid) {
		return getHibernateTemplate().get(entityClass, uuid);
	}

	/**
	 * 更新数据
	 */
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	/**
	 * 记录条件查询的总记录数
	 */
	public long getCount(T t1, T t2, Object param) {
		DetachedCriteria dc = getDetachedCriteria(t1, t2, param);
		dc.setProjection(Projections.rowCount());
		return (Long) getHibernateTemplate().findByCriteria(dc).get(0);
	}

	/**
	 * 由子类来实现
	 * 拿来给子类重写的方法
	 * @param t1
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(T t1, T t2, Object param) {
		return null;
	}

}
