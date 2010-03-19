package org.kyerp.web.controller.crm;

import java.math.BigDecimal;

/**
 * @author y109 2010-2-2下午04:00:23
 */
public class ClientExtGridRow {
	private Long		id;
	/** 分类 */
	private long		typeId;
	private String		typeName;
	/** 建立时间 */
	private String		createTime;
	/** 修改时间 */
	private String		updateTime;
	/** 编号 **/
	private String		serialNumber;
	/** 供应商简称 **/
	private String		name;
	/** 供应商简拼 **/
	private String		nameSpell;
	/** 供应商全称 **/
	private String		fullName;
	/** 是否可见 **/
	private Boolean		visible;
	/** 备注 */
	private String		remark;
	/** 地址 */
	private String		address;
	/** 邮编 */
	private String		postcode;
	/** 电话 */
	private String		phone;
	/** 传真 */
	private String		fax;
	/** 网站 */
	private String		www;
	/** 邮箱 */
	private String		email;
	/** 助记码 */
	private String		helpCode;
	/** 应付款 */
	private BigDecimal	accountReceivable;
	/** logo图片路径 如:/images/brand/2008/12/12/ooo.gif" **/
	private String		logopath;

	ClientExtGridRow() {
	}

	public Long getId() {
		return id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWww() {
		return www;
	}

	public void setWww(String www) {
		this.www = www;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public BigDecimal getAccountReceivable() {
		return accountReceivable;
	}

	public void setAccountReceivable(BigDecimal accountReceivable) {
		this.accountReceivable = accountReceivable;
	}

	public String getLogopath() {
		return logopath;
	}

	public void setLogopath(String logopath) {
		this.logopath = logopath;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameSpell() {
		return nameSpell;
	}

	public void setNameSpell(String nameSpell) {
		this.nameSpell = nameSpell;
	}

	public String getFullName() {
		return fullName;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
