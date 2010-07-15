package org.kyerp.domain.common.view;

/**
 * Excel 标题行项目
 *org.kyerp.domain.common.view.ExcelTitleColumn.java
 * 
 * @author y109
 *         2010-4-20下午11:40:50
 */
public class ExcelTitleColumn{
	private String	name;
	private int		width;

	public ExcelTitleColumn() {
	}

	/**
	 * @param name
	 * @param width
	 */
	public ExcelTitleColumn(String name, int width) {
		super();
		this.name = name;
		this.width = width;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
