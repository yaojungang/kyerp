package org.kyerp.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 定义标准的rest方法以对应实体对象的操作,以达到统一rest的方法名称,
 * 还可以避免子类需要重复编写@RequestMapping annotation.
 * 
 * 子类要实现某功能只需覆盖下面的方法即可.
 * 注意: 覆盖时请使用@Override,以确保不会发生错误
 * <pre>
 * /userinfo                => index()  
 * /userinfo/new            => _new()  
 * /userinfo/{id}           => show()  
 * /userinfo/{id}/edit      => edit()  
 * /userinfo        POST    => create()  
 * /userinfo/{id}   PUT     => update()  
 * /userinfo/{id}   DELETE  => delete()  
 * /userinfo        DELETE  => batchDelete()  
 * </pre>
 * @author badqiu
 */

public interface ICRUDController<Entity,PK> {
	@RequestMapping
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response,Entity model);
	
	/** 进入新增 */
	@RequestMapping(value="/new")
	public ModelAndView _new(HttpServletRequest request,HttpServletResponse response,Entity model) throws Exception;
	
	/** 显示 */
	@RequestMapping(value="/{id}")
	public ModelAndView show(@PathVariable PK id) throws Exception;
	
	/** 编辑 */
	@RequestMapping(value="/{id}/edit")
	public ModelAndView edit(@PathVariable PK id) throws Exception;
	
	/** 保存新增 */
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request,HttpServletResponse response,Entity model) throws Exception;
	
	/** 保存更新 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ModelAndView update(@PathVariable PK id,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	/** 删除 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable PK id);

	/** 批量删除 */
	@RequestMapping(method=RequestMethod.DELETE)
	public ModelAndView batchDelete(@RequestParam("items") PK[] items);
}
