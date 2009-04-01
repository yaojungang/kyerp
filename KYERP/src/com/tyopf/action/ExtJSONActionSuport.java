package com.tyopf.action;

import com.opensymphony.xwork2.ActionSupport;

public class ExtJSONActionSuport extends ActionSupport {
	private static final long serialVersionUID = 7746215551605686719L;
	private int totalCount = 0;// 总数
	private transient int start = 0;// 开始数
	private transient int limit = 10;// 限制数量
	private String jsonString = "";
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getJsonString() {
		return jsonString;
	}
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
	public String jsonExecute() throws Exception {
		return super.execute();
	}

}
