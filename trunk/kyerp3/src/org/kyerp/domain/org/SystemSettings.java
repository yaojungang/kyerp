package org.kyerp.domain.org;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SystemSettings implements Serializable {
	private static final long	serialVersionUID	= -9047830169332495084L;
	@Id
	@GeneratedValue
	private Integer				id;
	private String				variableName;
	private String				varValue;
	private String				remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public SystemSettings() {
	}

	public SystemSettings(String variableName) {
		this.variableName = variableName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SystemSettings(String variableName, String varValue) {
		this.variableName = variableName;
		this.varValue = varValue;
	}

	public String getVariableName() {
		return this.variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getVarValue() {
		return this.varValue;
	}

	public void setVarValue(String varValue) {
		this.varValue = varValue;
	}

}
