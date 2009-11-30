/**
 * 数据字典
 */
package org.kyerp.domain.base;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author y109 2009-11-29下午02:00:07
 */
@Entity
public class DataDic implements Serializable {
	/**
	 * 数据字典
	 */
	private static final long	serialVersionUID	= 5465515024216743529L;
	/** id */
	@Id
	@GeneratedValue
	private long				id;
	/** 数据类型 */
	private DateDicType			dateDicType;
	/** 数据内容 */
	private String				dictText;
	/** 数据内容排序 */
	private int					dictOrder;
	/** 数据内容 拼音首字母 */
	private String				dictSpell;
	/** 数据内容 全拼 */
	private String				dictAllSpell;

	public DataDic() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
