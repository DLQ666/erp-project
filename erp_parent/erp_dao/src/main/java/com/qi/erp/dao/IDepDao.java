package com.qi.erp.dao;

import java.util.List;

import com.qi.erp.entity.Dep;

/**
 * @author dlq96
 * 部门数据访问接口
 */
public interface IDepDao {
	
	/**
	 * 查询部门全部列表
	 * @return
	 */
	
	public List<Dep> getList();
	
	/**
	 * 条件查询
	 * @param dep1
	 */
	public List<Dep> getList(Dep dep1,Dep dep2,Object param,int firstResult,int maxResults);
	
	/**
	 * 计算条件查询的总记录数
	 * @param dep1
	 * @return
	 */
	public long getCount(Dep dep1, Dep dep2, Object param);
	
	/**
	 * 新增部门
	 * @param dep
	 */
	public void add(Dep dep);
	
	
	/**
	 * 删除部门
	 * @param uuid
	 */
	public void delete(Long uuid);
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @return
	 */
	public Dep get(Long uuid);
	
	/**
	 * 更新部门数据
	 */
	public void update(Dep dep);

}
