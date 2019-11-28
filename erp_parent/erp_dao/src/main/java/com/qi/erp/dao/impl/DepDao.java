package com.qi.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.qi.erp.dao.IDepDao;
import com.qi.erp.entity.Dep;

/**
 * @author dlq96 部门数据访问类
 *
 */
public class DepDao extends BaseDao<Dep> implements IDepDao {
	
//	/**
//	 * 查询所有部门信息
//	 */
//	@SuppressWarnings("unchecked")
//	public List<Dep> getList() {
//		return (List<Dep>) this.getHibernateTemplate().find("from Dep");
//	}
//
//	/**
//	 * 条件查询
//	 */
//	@SuppressWarnings("unchecked")
//	public List<Dep> getList(Dep dep1, Dep dep2, Object param, int firstResult, int maxResults) {
//		DetachedCriteria dc = getDetachedCriteria(dep1, dep2, param);
//		return (List<Dep>) getHibernateTemplate().findByCriteria(dc, firstResult, maxResults);
//	}
//
//	/**
//	 * 新增部门
//	 */
//	public void add(Dep dep) {
//		this.getHibernateTemplate().save(dep);
//	}
//
//	/**
//	 * 删除部门
//	 */
//	public void delete(Long uuid) {
//		// 让对象进入持久化状态
//		Dep dep = this.getHibernateTemplate().get(Dep.class, uuid);
//		// 删除持久化状态
//		this.getHibernateTemplate().delete(dep);
//	}
//
//	/**
//	 * 通过编号查询对象
//	 * @param uuid
//	 * @return
//	 */
//	public Dep get(Long uuid) {
//		return getHibernateTemplate().get(Dep.class, uuid);
//	}
//
//	/**
//	 * 更新数据
//	 */
//	public void update(Dep t) {
//		this.getHibernateTemplate().update(t);
//	}

	/**
	 * 记录条件查询的总记录数
	 */
	public long getCount(Dep dep1, Dep dep2, Object param) {
		DetachedCriteria dc = getDetachedCriteria(dep1, dep2, param);
		dc.setProjection(Projections.rowCount());
		return (Long) getHibernateTemplate().findByCriteria(dc).get(0);
	}
	public DetachedCriteria getDetachedCriteria(Dep dep1, Dep dep2, Object param) {
		DetachedCriteria dc = DetachedCriteria.forClass(Dep.class);
		if (dep1 != null) {
			if (dep1.getName() != null && dep1.getName().length() > 0) {
				dc.add(Restrictions.like("name", dep1.getName(), MatchMode.ANYWHERE));
			}
			if (dep1.getTele() != null && dep1.getTele().length() > 0) {
				dc.add(Restrictions.like("tele", dep1.getTele(), MatchMode.ANYWHERE));
			}
		}
		return dc;
	}
	
}
