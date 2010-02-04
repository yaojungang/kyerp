package org.kyerp.domain.base.views;

import java.util.List;

/**
 * 用于构建Extjs的Grid
 * 
 * @author y109 2010-1-20下午05:06:11
 */
public class ExtGridList<T> {
	private Long	totalProperty;
	private Integer	start	= 0;
	private Integer	limit	= 20;
	private List<T>	rows;

	public Long getTotalProperty() {
		return totalProperty;
	}

	public void setTotalProperty(Long totalProperty) {
		this.totalProperty = totalProperty;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
