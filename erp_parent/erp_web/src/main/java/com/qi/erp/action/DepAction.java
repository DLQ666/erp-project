package com.qi.erp.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.qi.erp.biz.IDepBiz;
import com.qi.erp.entity.Dep;

/**
 * 部门Action
 * 
 * @author dlq96
 *
 */
public class DepAction {

	private IDepBiz depBiz;

	public void setDepBiz(IDepBiz depBiz) {
		this.depBiz = depBiz;
	}

	// dep1属性驱动 ：条件查询
	
	private Dep dep1;
	private Dep dep2;
	private Object param;

	public Dep getDep1() {
		return dep1;
	}

	public void setDep1(Dep dep1) {
		this.dep1 = dep1;
	}

	public Dep getDep2() {
		return dep2;
	}

	public void setDep2(Dep dep2) {
		this.dep2 = dep2;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	// 分页
	private int page;// 页码
	private int rows;// 每页记录数

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * 查询所有部门
	 */
	public void list() {
		//调用部门业务查询所有部门信息
		List<Dep> list = depBiz.getList();
		//把部门列表转换成json字符串
		String jsonString = JSON.toJSONString(list);
		write(jsonString);
	}

	//新增属性驱动
	private Dep dep;

	public Dep getDep() {
		return dep;
	}

	public void setDep(Dep dep) {
		this.dep = dep;
	}

	/**
	 * 新增
	 */
	public void add() {
		//{"sucess"：true，"message":""}
		//返回前端的JSon数据
		Map<String, Object> rtn = new HashMap<String, Object>();
		try {
			depBiz.add(dep);
			rtn.put("success", true);
			rtn.put("message", "新增成功");
		} catch (Exception e) {
			e.printStackTrace();
			rtn.put("success", false);
			rtn.put("message", "新增失败");
		}
		write(JSON.toJSONString(rtn));
	}
	
	/**
	 * 删除部门
	 * @param uuid
	 */
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public void delete() {
		try {
			depBiz.delete(id);
			ajaxReturn(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "删除失败");
		}
	}

	/**
	 * 通过编号查询部门对象
	 */
	public void get() {
		Dep dep = depBiz.get(id);
		String jsonString = JSON.toJSONString(dep);
		//{"name":"党支部","tele":"555","uuid":34}
		System.out.println("转换前" + jsonString);
		String jsonStringAfter = mapData(jsonString, "dep");
		System.out.println("转换后" + jsonStringAfter);
		write(jsonStringAfter);
	}
	
	/**
	 * 修改数据
	 */
	public void update() {
		try {
			depBiz.update(dep);
			ajaxReturn(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "修改失败");
		}
	}
	
	/**
	 * 条件查询
	 */
	public void getList() {
		int firstResult = (page - 1) * rows;
		List<Dep> list = depBiz.getList(dep1, dep2, param, firstResult, rows);
		long total = depBiz.getCount(dep1,dep2,param);
		// {total: total,rows:[]}
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", total);
		mapData.put("rows", list);
		String jsonString = JSON.toJSONString(mapData);
		write(jsonString);
		
	}
	private void write(String jsonString) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void ajaxReturn(boolean success, String message) {
		// 返回前端的JSon数据
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success", success);
		rtn.put("message", message);
		write(JSON.toJSONString(rtn));
	}
	
	/**
	 * //{"name":"党支部","tele":"555","uuid":34}
	 * @param jsonString JSON数据字符串
	 * @param prefix  要加上的前缀
	 * @return {"dep.name":"党支部","dep.tele":"555","dep.uuid":34}
	 */
	public String mapData(String jsonString, String prefix) {
		Map<String, Object> map = JSON.parseObject(jsonString);
		//存储加上前缀后key的值
		Map<String, Object> dataMap = new HashMap<String, Object>();
		//给每个key值加上前缀
		for(String key : map.keySet()) {
			dataMap.put(prefix + "." + key, map.get(key));
		}
		return JSON.toJSONString(dataMap);
	}
}
