/**
 * 客户实体 ： 表示某一个公司或者组织
 */
package org.kyerp.domain.crm;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.kyerp.domain.BaseDomain;
import org.kyerp.domain.print.Presswork;

/**
 * @author y109 2009-11-23下午03:05:17
 */
@Entity
public class Client extends BaseDomain implements java.io.Serializable {
	private static final long	serialVersionUID	= 1L;	;
	/** 客户名称 */
	private String				name;

	/** 客户类型 */
	@ManyToOne
	private ClientType			clientType;

	/** 下属联系人 */
	@ManyToMany(targetEntity = Contact.class, cascade = { CascadeType.ALL })
	private List<Contact>		contacts;

	/** 下属任务单 */
	@OneToMany(mappedBy = "client")
	private List<Presswork>		pressworks;
	/** 编码 */
	private String				serialNumber;
	/** 简拼 **/
	private String				nameSpell;
	/** 全称 **/
	private String				fullName;
	/** 是否可见 **/
	private Boolean				visible				= true;
	/** 备注 */
	private String				remark;
	/** 地址 */
	private String				address;
	/** 邮编 */
	private String				postcode;
	/** 电话 */
	private String				phone;
	/** 传真 */
	private String				fax;
	/** 网站 */
	private String				www;
	/** 邮箱 */
	private String				email;
	/** 助记码 */
	private String				helpCode;
	/** 应收款 */
	private BigDecimal			accountReceivable;
	/** logo图片路径 如:/images/brand/2008/12/12/ooo.gif" **/
	private String				logopath;

	public Client() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Presswork> getPressworks() {
		return pressworks;
	}

	public void setPressworks(List<Presswork> pressworks) {
		this.pressworks = pressworks;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
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

}
