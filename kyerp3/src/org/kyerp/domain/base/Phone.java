package org.kyerp.domain.base;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 电话实体
 * 
 * @author y109 2009-11-23下午03:07:17
 */
@Entity
public class Phone implements java.io.Serializable {
	private static final long	serialVersionUID	= -6001738136453827260L;

	@Id
	@GeneratedValue
	private long				id;
	/** 建立日期 */
	private final Date			createTime			= new Date();
	/** 号码 */
	private String				number;
	/** 号码类型 */
	@Enumerated(EnumType.STRING)
	private PhoneType			type;

	public Phone() {
	}

	public Date getCreateTime() {
		return createTime;
	}

	public long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public PhoneType getType() {
		return type;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setType(PhoneType type) {
		this.type = type;
	}
}
