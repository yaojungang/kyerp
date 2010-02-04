/**
 * 客户实体 ： 表示某一个公司或者组织
 */
package org.kyerp.domain.crm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.kyerp.domain.base.Address;
import org.kyerp.domain.base.Phone;
import org.kyerp.domain.print.Presswork;


/**
 * @author y109 2009-11-23下午03:05:17
 */
@Entity
public class Customer implements java.io.Serializable {
	private static final long		serialVersionUID	= 1L;
	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long					id;													;
	/** 客户名称 */
	private String					name;

	/** 客户类型 */
	private CustomerType			type				= CustomerType.DISPERSED;

	/** 地址 */
	@OneToOne(cascade = { CascadeType.ALL })
	private Address					address;
	/** 电话 */
	@OneToMany(cascade = { CascadeType.ALL })
	private List<Phone>				phones				= new ArrayList<Phone>();

	/** 下属联系人 */
	@ManyToMany(targetEntity = Contact.class, cascade = { CascadeType.ALL })
	private final List<Contact>		contacts			= new ArrayList<Contact>();

	/** 下属任务单 */
	@OneToMany(mappedBy = "customer")
	private final List<Presswork>	pressworks			= new ArrayList<Presswork>();

	/** 建立时间 */
	private Date					createTime;
	/** 更新时间 */
	private Date					updateTime;

	public Customer() {
	}

	/** 在对象新建前保存建立时间 */
	@PrePersist
	public void prePersist() {
		this.createTime = new Date();
	}

	/** 在对象更新前保存修改时间 */
	@PreUpdate
	void preUpdate() {
		this.updateTime = new Date();
	}

	public Address getAddress() {
		return address;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public List<Presswork> getPressworks() {
		return pressworks;
	}

	public CustomerType getType() {
		return type;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
