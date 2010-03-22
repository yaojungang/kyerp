package org.kyerp.domain.base.views;

import java.util.List;

/**
 * 用于ExtjsTree
 * 
 * @author y109 2009-12-16下午10:14:40
 */
public class ExtTreeNode{
	private String				id;
	private String				parentId;
	private String				text;
	private String				qtip;
	private String				icon;
	private Boolean				leaf		= true;
	private Boolean				expanded;
	private String				href		= "";
	private String				hrefTarget	= "";
	private List<ExtTreeNode>	children;

	public ExtTreeNode() {
	}

	/**
	 * @param id
	 * @param parentId
	 * @param text
	 */
	public ExtTreeNode(String id, String parentId, String text) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getQtip() {
		return qtip;
	}

	public void setQtip(String qtip) {
		this.qtip = qtip;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public List<ExtTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<ExtTreeNode> children) {
		this.children = children;
	}

}
