package org.kyerp.domain.org;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SystemModules implements Serializable {
	/**
	 * 
	 */
	private static final long		serialVersionUID	= 5493436465149931625L;
	@Id
	@GeneratedValue
	private long					id;
	@OneToMany(mappedBy = "systemModule")
	private List<SystemFunctions>	systemFunctions		= new ArrayList<SystemFunctions>();
	private String					name;
	private String					chineseName;

	public SystemModules() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<SystemFunctions> getSystemFunctions() {
		return systemFunctions;
	}

	public void setSystemFunctions(List<SystemFunctions> systemFunctions) {
		this.systemFunctions = systemFunctions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

}
