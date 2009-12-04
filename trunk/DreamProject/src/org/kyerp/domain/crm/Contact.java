/**
 * 联系人实体
 */
package org.kyerp.domain.crm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.kyerp.domain.base.Address;
import org.kyerp.domain.base.Phone;
import org.kyerp.domain.print.Presswork;

/**
 * @author y109 2009-11-23下午03:05:51
 */
@Entity
public class Contact {
	@Id
	@GeneratedValue
	private Long					id;

	/** 创建时间 */
	private Date					createDate;
	/** 所属客户 */
	@ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "contacts")
	private final List<Customer>	customers	= new ArrayList<Customer>();
	/** 下属任务单 */
	@OneToMany(mappedBy = "contact")
	@OrderBy("createTime ASC")
	private final List<Presswork>	pressworks	= new ArrayList<Presswork>();

	/** 地址 */
	@OneToMany(cascade = { CascadeType.ALL })
	private List<Address>			address		= new ArrayList<Address>();
	/** 电话 */
	@OneToMany(cascade = { CascadeType.ALL })
	private List<Phone>				phones		= new ArrayList<Phone>();

	public Contact() {
	}

	public List<Address> getAddress() {
		return address;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public Long getId() {
		return id;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public List<Presswork> getPressworks() {
		return pressworks;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

}
