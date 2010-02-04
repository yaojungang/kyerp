/**
 * 联系人实体
 */
package org.kyerp.domain.crm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.kyerp.domain.base.Address;
import org.kyerp.domain.base.Phone;
import org.kyerp.domain.print.Presswork;


/**
 * @author y109 2009-11-23下午03:05:51
 */
@Entity
public class Contact implements Serializable {
	private static final long		serialVersionUID	= 7336812939931528029L;
	@Id
	@GeneratedValue
	private Long					id;
	/** 客户名称 */
	private String					name;
	/** 所属客户 */
	@ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "contacts")
	private final List<Customer>	customers			= new ArrayList<Customer>();
	/** 下属任务单 */
	@OneToMany(mappedBy = "contact")
	private final List<Presswork>	pressworks			= new ArrayList<Presswork>();

	/** 地址 */
	@OneToMany(cascade = { CascadeType.ALL })
	private List<Address>			address				= new ArrayList<Address>();
	/** 电话 */
	@OneToMany(cascade = { CascadeType.ALL })
	private List<Phone>				phones				= new ArrayList<Phone>();
	/** 建立时间 */
	private Date					createTime;
	/** 修改时间 */
	private Date					updateTime;

	public Contact() {
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

	public Long getId() {
		return id;
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

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
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

	public List<Customer> getCustomers() {
		return customers;
	}

	public List<Presswork> getPressworks() {
		return pressworks;
	}

}
