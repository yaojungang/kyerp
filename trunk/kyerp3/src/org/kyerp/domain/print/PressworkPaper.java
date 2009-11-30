package org.kyerp.domain.print;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.kyerp.domain.base.MaterialFrom;
import org.kyerp.domain.warehouse.PaperOfMaterial;


/**
 * 任务单：开纸项目
 * 
 * @author y109 2009-11-29下午09:56:12
 */
@Entity
public class PressworkPaper implements Serializable {
	private static final long	serialVersionUID	= 1993485346483313604L;
	@Id
	@GeneratedValue
	private long				id;
	/** 所属任务单 */
	@ManyToOne
	private Presswork			presswork;
	/** 项目名称：封面，正文，插页 */
	private String				itemName;
	/** 纸张 */
	private PaperOfMaterial		paper;
	/** 纸张：来源 */
	private MaterialFrom		paperFrom;
	/** 开纸尺寸:8,16,32 */
	private int					cutSize;

	/** 纸张(小)：正数 */
	private int					paperStandAmount;
	/** 纸张(小)：加放 */
	private int					paperAddAmount;
	/** 纸张(小)：总数 */
	private int					paperAmount;

	/** 纸张(原张)：总数 */
	private int					paperFullAmount;

	/** 开纸长 */
	private int					cutHight;
	/** 开纸宽 */
	private int					cutWidth;

	public PressworkPaper() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Presswork getPresswork() {
		return presswork;
	}

	public void setPresswork(Presswork presswork) {
		this.presswork = presswork;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public PaperOfMaterial getPaper() {
		return paper;
	}

	public void setPaper(PaperOfMaterial paper) {
		this.paper = paper;
	}

	public MaterialFrom getPaperFrom() {
		return paperFrom;
	}

	public void setPaperFrom(MaterialFrom paperFrom) {
		this.paperFrom = paperFrom;
	}

	public int getCutSize() {
		return cutSize;
	}

	public void setCutSize(int cutSize) {
		this.cutSize = cutSize;
	}

	public int getPaperStandAmount() {
		return paperStandAmount;
	}

	public void setPaperStandAmount(int paperStandAmount) {
		this.paperStandAmount = paperStandAmount;
	}

	public int getPaperAddAmount() {
		return paperAddAmount;
	}

	public void setPaperAddAmount(int paperAddAmount) {
		this.paperAddAmount = paperAddAmount;
	}

	public int getPaperAmount() {
		return paperAmount;
	}

	public void setPaperAmount(int paperAmount) {
		this.paperAmount = paperAmount;
	}

	public int getPaperFullAmount() {
		return paperFullAmount;
	}

	public void setPaperFullAmount(int paperFullAmount) {
		this.paperFullAmount = paperFullAmount;
	}

	public int getCutHight() {
		return cutHight;
	}

	public void setCutHight(int cutHight) {
		this.cutHight = cutHight;
	}

	public int getCutWidth() {
		return cutWidth;
	}

	public void setCutWidth(int cutWidth) {
		this.cutWidth = cutWidth;
	}

}
