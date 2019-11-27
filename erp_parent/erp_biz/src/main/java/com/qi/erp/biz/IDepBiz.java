package com.qi.erp.biz;

import java.util.List;

import com.qi.erp.entity.Dep;

/**
 * 业务逻辑类接口
 * @author dlq96
 *
 */
public interface IDepBiz {
	/**
	 * 查询全部列表接口
	 * @return
	 */
	public List<Dep> getList();
	
	/**
	 * 新增
	 * @param dep
	 */
	public void add(Dep dep);
	
	/**
	 *删除部门
	 */
	public void delete(Long uuid);
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @return
	 */
	public Dep get(Long uuid);
	
	/**
	 * 分页
	 * @param dep1
	 * @return
	 */
	public List<Dep> getList(Dep dep1,Dep dep2,Object param,int firstResult,int maxResults);
	
	/**
	 * 记录总记录数
	 * @param dep1
	 * @return
	 */
	public long getCount(Dep dep1, Dep dep2, Object param);
	
	/**
	 * 更新部门数据
	 */
	public void update(Dep dep);
}
