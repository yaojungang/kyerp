package org.kyerp.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public abstract class CRUDController<Entity,PK> extends BaseController implements ICRUDController<Entity, PK> {
	@RequestMapping
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response,Entity model) {
		throw new UnsupportedOperationException("not yet implement");
	}
	
	/** 进入新增 */
	@RequestMapping(value="/new")
	public ModelAndView _new(HttpServletRequest request,HttpServletResponse response,Entity model) throws Exception {
		throw new UnsupportedOperationException("not yet implement");
	}
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public ModelAndView show(@PathVariable PK id) throws Exception {
		throw new UnsupportedOperationException("not yet implement");
	}
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public ModelAndView edit(@PathVariable PK id) throws Exception {
		throw new UnsupportedOperationException("not yet implement");
	}
	
	/** 保存新增 */
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request,HttpServletResponse response,Entity model) throws Exception {
		throw new UnsupportedOperationException("not yet implement");
	}
	
	/** 保存更新 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ModelAndView update(@PathVariable PK id,HttpServletRequest request,HttpServletResponse response) throws Exception {
		throw new UnsupportedOperationException("not yet implement");
	}
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable PK id) {
		throw new UnsupportedOperationException("not yet implement");
	}

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public ModelAndView batchDelete(@RequestParam("items") PK[] items) {
		throw new UnsupportedOperationException("not yet implement");
	}
}
