package org.kyerp.domain.print;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 印刷机
 * 
 * @author y109 2009-11-29下午10:51:28
 */
@Entity
public class PressMachine implements Serializable {
	private static final long	serialVersionUID	= -1516497659449326498L;
	@Id
	@GeneratedValue
	private long				id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
