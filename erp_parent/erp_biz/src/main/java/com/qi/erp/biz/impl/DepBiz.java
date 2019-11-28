package com.qi.erp.biz.impl;

import com.qi.erp.biz.IDepBiz;
import com.qi.erp.dao.IDepDao;
import com.qi.erp.entity.Dep;

/**
 * 业务逻辑类
 * @author dlq96
 */
public class DepBiz extends BaseBiz<Dep> implements IDepBiz {

	/** 数据访问层注入 */
	private IDepDao depDao;

	public void setDepDao(IDepDao depDao) {
		this.depDao = depDao;
		super.setBaseDao(this.depDao);
	}
	
}
