package org.kyerp.domain.base.views;

/**
 * 用于ExtjsTree
 * 
 * @author y109 2009-12-16下午10:14:40
 */
public class ExtTreeNode {
	private String	id;
	private String	parentId;
	private String	text;
	private String	qtip;
	private String	icon;
	private Boolean	leaf		= true;
	private Boolean	expanded;
	private String	href		= "";
	private String	hrefTarget	= "";

	public ExtTreeNode() {
	}

	/**
	 * @param parentId
	 * @param text
	 * @param leaf
	 */
	public ExtTreeNode(String parentId, String text, Boolean leaf) {
		super();
		this.parentId = parentId;
		this.text = text;
		this.leaf = leaf;
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

}
