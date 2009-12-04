/**
 * 
 */
package org.kyerp.domain.print;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author y109
 * 
 */
@Entity
public class PressworkElement implements java.io.Serializable {

	/**
	 * 常规工艺项目表
	 */
	private static final long	serialVersionUID	= 6807305912295424600L;
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	private long				id;
	/** 所属任务单 */
	@ManyToOne
	@JoinColumn(name = "presswork_id")
	private Presswork			presswork;

	public PressworkElement() {
	}

	public long getId() {
		return id;
	}

	public Presswork getPresswork() {
		return presswork;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPresswork(Presswork presswork) {
		this.presswork = presswork;
	};

}
