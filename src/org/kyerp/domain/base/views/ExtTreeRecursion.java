/**
 * 用于构建Ext的Tree
 */
package org.kyerp.domain.base.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author y109 2010-1-29上午10:49:34
 */
public class ExtTreeRecursion{

	public ExtTreeRecursion() {
	}

	private StringBuffer	returnStr	= new StringBuffer();

	public void recursionFn(List<ExtTreeNode> list, ExtTreeNode node) {
		List<ExtTreeNode> childList = getChildList(list, node);
		if(childList.size() > 0) {
			getReturnStr().append("{id:");
			getReturnStr().append("\"");
			getReturnStr().append(node.getId());
			getReturnStr().append("\"");
			getReturnStr().append(",text:\"");
			getReturnStr().append(node.getText());
			getReturnStr().append("\",expanded:");
			getReturnStr().append(node.getExpanded());
			getReturnStr().append(",parentId:");
			getReturnStr().append("\"");
			getReturnStr().append(node.getParentId());
			getReturnStr().append("\"");
			getReturnStr().append(",children:[");
			Iterator<ExtTreeNode> it = childList.iterator();
			while (it.hasNext()) {
				ExtTreeNode n = (ExtTreeNode) it.next();
				recursionFn(list, n);
			}
			getReturnStr().append("]},");
		} else {
			getReturnStr().append("{id:");
			getReturnStr().append("\"");
			getReturnStr().append(node.getId());
			getReturnStr().append("\"");
			getReturnStr().append(",text:\"");
			getReturnStr().append(node.getText());
			getReturnStr().append("\",parentId:");
			getReturnStr().append("\"");
			getReturnStr().append(node.getParentId());
			getReturnStr().append("\"");
			getReturnStr().append(",leaf:true},");
		}

	}

	public boolean hasChild(List<ExtTreeNode> list, ExtTreeNode node) { // 判断是否有子节点
		return getChildList(list, node).size() > 0 ? true : false;
	}

	public List<ExtTreeNode> getChildList(List<ExtTreeNode> list, ExtTreeNode node) { // 得到子节点列表
		List<ExtTreeNode> li = new ArrayList<ExtTreeNode>();
		synchronized (list) {
			Iterator<ExtTreeNode> it = list.iterator();
			while (it.hasNext()) {
				ExtTreeNode n = (ExtTreeNode) it.next();
				if(null != n.getParentId() && n.getParentId().equals(node.getId())) {
					li.add(n);
					it.remove(); // // list 作为全局变量， 这里remove所以要线程同步 ，
					// 如果不remove则不要线程同步也可。
				}
			}
		}
		return li;
	}

	public String modifyStr(String returnStr) {// 修饰一下才能满足Extjs的Json格式
		return ("[" + returnStr + "]").replaceAll(",]", "]");

	}

	public void setReturnStr(StringBuffer returnStr) {
		this.returnStr = returnStr;
	}

	public StringBuffer getReturnStr() {
		return returnStr;
	}

}
