/**
 * 数据字典
 */
package org.kyerp.domain.common;

import java.io.Serializable;

import javax.persistence.Entity;

import org.kyerp.domain.BaseDomain;

/**
 * @author y109 2009-11-29下午02:00:07
 */
@Entity
public class DataDic extends BaseDomain implements Serializable {
	/**
	 * 数据字典
	 */
	private static final long	serialVersionUID	= 5465515024216743529L;
	/** 数据类型 */
	private DateDicType			dateDicType;
	/** 数据内容 */
	private String				dictText;
	/** 数据内容排序 */
	private int					dictOrder;
	/** 数据内容简拼 */
	private String				dictSpell;
	/** 数据内容 全拼 */
	private String				dictAllSpell;

	public DataDic() {
	}

	public DateDicType getDateDicType() {
		return dateDicType;
	}

	public void setDateDicType(DateDicType dateDicType) {
		this.dateDicType = dateDicType;
	}

	public String getDictText() {
		return dictText;
	}

	public void setDictText(String dictText) {
		this.dictText = dictText;
	}

	public String getDictSpell() {
		return dictSpell;
	}

	public void setDictSpell(String dictSpell) {
		this.dictSpell = dictSpell;
	}

	public String getDictAllSpell() {
		return dictAllSpell;
	}

	public void setDictAllSpell(String dictAllSpell) {
		this.dictAllSpell = dictAllSpell;
	}

	public int getDictOrder() {
		return dictOrder;
	}

	public void setDictOrder(int dictOrder) {
		this.dictOrder = dictOrder;
	}

}
