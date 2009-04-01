package com.tyopf.util;

@SuppressWarnings("unused")
public class Pager {

	private int currentPage;		//��ǰҳ
	private int pageSize = 50;		//ÿҳ����
	private int totalSize;			//������
	private int totalPage;			//��ҳ��
	

	private boolean hasFirst;
	private boolean hasPrevious;
	private boolean hasNext;
	private boolean hasLast;
	
	public Pager(int currentPage,int totalSize){
		
		this.currentPage = currentPage;
		this.totalSize = totalSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public boolean isHasFirst() {
		
		if(currentPage == 1)
			return false;
		return true;
	}
	public void setHasFirst(boolean hasFirst) {
		this.hasFirst = hasFirst;
	}
	public boolean isHasLast() {
		
		if(currentPage == getTotalPage())
			return false;
		return true;
	}
	public void setHasLast(boolean hasLast) {
		this.hasLast = hasLast;
	}
	public boolean isHasNext() {
		
		if(isHasLast())
			return true;
		return false;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public boolean isHasPrevious() {
		
		if(isHasFirst())
			return true;
		return false;
	}
	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		
		totalPage = totalSize / pageSize;
		if(totalSize % pageSize != 0)
			totalPage++;
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
}
