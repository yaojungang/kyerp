/**
 * 联系人实体
 */
package org.kyerp.domain.crm;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.kyerp.domain.BaseDomain;
import org.kyerp.domain.base.Address;
import org.kyerp.domain.base.Phone;
import org.kyerp.domain.print.Presswork;

/**
 * @author y109 2009-11-23下午03:05:51
 */
@Entity
public class Contact extends BaseDomain implements Serializable {
	private static final long	serialVersionUID	= 1L;
	/** 客户名称 */
	private String				name;
	/** 所属客户 */
	@ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "contacts")
	private List<Client>		clients;
	/** 下属任务单 */
	@OneToMany(mappedBy = "contact")
	private List<Presswork>		pressworks;

	/** 地址 */
	@OneToMany(cascade = { CascadeType.ALL })
	private List<Address>		address;
	/** 电话 */
	@OneToMany(cascade = { CascadeType.ALL })
	private List<Phone>			phones;

	public Contact() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Presswork> getPressworks() {
		return pressworks;
	}

	public void setPressworks(List<Presswork> pressworks) {
		this.pressworks = pressworks;
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

}
