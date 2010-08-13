package org.kyerp.domain.common.view;


/**
 * Excel 标题行项目
 *org.kyerp.domain.common.view.ExcelTitleColumn.java
 * 
 * @author y109
 *         2010-4-20下午11:40:50
 */
public class ExcelTitleCell{
	private String	title;
	private int		width;

	public ExcelTitleCell() {
	}

	public ExcelTitleCell(String title, int width) {
		super();
		this.title = title;
		this.width = width;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
}
