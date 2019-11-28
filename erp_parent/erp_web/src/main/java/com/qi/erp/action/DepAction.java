package com.qi.erp.action;

import com.qi.erp.biz.IDepBiz;
import com.qi.erp.entity.Dep;

/**
 * 部门Action
 * @author dlq96
 *
 */
public class DepAction extends BaseAction<Dep>{

	private IDepBiz depBiz;

	public void setDepBiz(IDepBiz depBiz) {
		this.depBiz = depBiz;
		super.setBaseBiz(this.depBiz);
	}

}
