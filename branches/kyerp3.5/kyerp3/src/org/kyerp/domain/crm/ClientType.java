/**
 * 客户类型
 */
package org.kyerp.domain.crm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.kyerp.domain.BaseDomain;

/**
 * @author y109 2009-11-23下午03:16:48
 */
@Entity
public class ClientType extends BaseDomain implements Serializable {
	private static final long		serialVersionUID	= 1L;
	/** 类别名称 **/
	private String					name;
	/** 编号 **/
	private String					serialNumber;
	/** 摘要 **/
	private String					note;
	/** 子类别 **/
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE }, mappedBy = "parentClientType")
	private final List<ClientType>	childClientTypes	= new ArrayList<ClientType>();
	/** 所属父类 **/
	@ManyToOne(cascade = CascadeType.REFRESH)
	private ClientType				parentClientType;

	public ClientType() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public ClientType getParentClientType() {
		return parentClientType;
	}

	public void setParentClientType(ClientType parentClientType) {
		this.parentClientType = parentClientType;
	}

	public List<ClientType> getChildClientTypes() {
		return childClientTypes;
	}

}
