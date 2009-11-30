package org.kyerp.domain.print;

/**
 * 生产任务单项目:制版印刷项目
 * */
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.kyerp.domain.warehouse.PlateOfMaterial;


@Entity
public class PressworkPress implements Serializable {
	private static final long	serialVersionUID	= -7784970638877997638L;
	@Id
	@GeneratedValue
	private long				id;
	/** 所属任务单 */
	@ManyToOne
	private Presswork			presswork;
	/** 项目名称：封面，正文，插页 */
	private String				itemName;
	/** 印版 */
	private PlateOfMaterial		plate;
	/** 印版套数 */
	private int					plateSeriesAmount;
	/** 印版张数 */
	private int					plateAmount;
	/** 印色 */
	private String				colors;
	/** 专色 */
	private String				specialColors;
	/** 印刷方式 */
	private String				pressType;
	/** 印刷台 */
	private PressMachine		pressMachine;

	public PressworkPress() {
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

	public PlateOfMaterial getPlate() {
		return plate;
	}

	public void setPlate(PlateOfMaterial plate) {
		this.plate = plate;
	}

	public int getPlateSeriesAmount() {
		return plateSeriesAmount;
	}

	public void setPlateSeriesAmount(int plateSeriesAmount) {
		this.plateSeriesAmount = plateSeriesAmount;
	}

	public int getPlateAmount() {
		return plateAmount;
	}

	public void setPlateAmount(int plateAmount) {
		this.plateAmount = plateAmount;
	}

	public String getColors() {
		return colors;
	}

	public void setColors(String colors) {
		this.colors = colors;
	}

	public String getSpecialColors() {
		return specialColors;
	}

	public void setSpecialColors(String specialColors) {
		this.specialColors = specialColors;
	}

	public String getPressType() {
		return pressType;
	}

	public void setPressType(String pressType) {
		this.pressType = pressType;
	}

	public PressMachine getPressMachine() {
		return pressMachine;
	}

	public void setPressMachine(PressMachine pressMachine) {
		this.pressMachine = pressMachine;
	}

}
