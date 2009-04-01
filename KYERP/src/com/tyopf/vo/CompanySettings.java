package com.tyopf.vo;

public class CompanySettings implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String variableName;
	private String varValue;

	public CompanySettings() {
	}

	public CompanySettings(String variableName) {
		this.variableName = variableName;
	}

	public CompanySettings(String variableName, String varValue) {
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
