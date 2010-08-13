package org.kyerp.domain.org;

import java.io.Serializable;

import javax.persistence.Entity;

import org.kyerp.domain.BaseDomain;

@Entity
public class SystemSettings extends BaseDomain implements Serializable {
	private static final long	serialVersionUID	= -9047830169332495084L;
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
