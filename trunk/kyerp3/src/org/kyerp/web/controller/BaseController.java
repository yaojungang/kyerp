/**
 * 
 */
package org.kyerp.web.controller;


/**
 * @author y109 2009-12-8上午10:15:20
 */

public class BaseController {
	public Integer	id;
	public Integer	page;

	public Integer getId() {
		return id < 1 ? 1 : id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPage() {
		page = null == page || page < 1 ? 1 : page;
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
