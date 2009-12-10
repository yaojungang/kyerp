package org.kyerp.domain.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.kyerp.domain.BaseDomain;
import org.kyerp.domain.print.Presswork;

/**
 * @author y109 2009-12-10下午08:43:21
 */
@Entity
public class DeliveryMaterialDetail extends BaseDomain implements Serializable {
	private static final long	serialVersionUID	= 1L;
	@ManyToOne
	private DeliveryMaterial	deliveryMaterial;
	/** 材料 **/
	@ManyToOne
	private Material			material;
	/** 单位 */
	private String				module;
	/** 价格 */
	private BigDecimal			price;
	/** 数量 */
	private Float				amount;
	/** 对应生产任务单 */
	private Presswork			presswork;

	public DeliveryMaterialDetail() {
	}

	public DeliveryMaterial getDeliveryMaterial() {
		return deliveryMaterial;
	}

	public void setDeliveryMaterial(DeliveryMaterial deliveryMaterial) {
		this.deliveryMaterial = deliveryMaterial;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Presswork getPresswork() {
		return presswork;
	}

	public void setPresswork(Presswork presswork) {
		this.presswork = presswork;
	}

}
