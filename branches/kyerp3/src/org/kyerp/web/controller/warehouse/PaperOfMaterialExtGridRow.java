package org.kyerp.web.controller.warehouse;

import java.math.BigDecimal;

/**
 *org.kyerp.web.controller.warehouse.PaperOfMaterialExtGridRow.java
 * 
 * @author y109
 *         2010-3-25上午11:43:32
 */
public class PaperOfMaterialExtGridRow{
	/** 纸张名称 */
	private String		paperName;
	/** 纸长(mm) */
	private int			paperHeight;
	/** 纸宽(mm) */
	private int			paperWidth;
	/** 纸张克重 */
	private int			paperWeight;
	/** 纸张吨价 */
	private BigDecimal	tonnePrice;
	/** 每平米价格 */
	private BigDecimal	squareMetrePrice;
	/** 每张价格 */
	private BigDecimal	pricePrePage;
	private Long		id;
	/** 编号 **/
	private String		serialNumber;
	/** 物料全称 **/
	private String		name;
	/** 物料名称 */
	private String		materialName;
	/** 规格 **/
	private String		specification;
	/** 类别 */
	private Long		materialCategoryId;
	private String		materialCategoryName;
	/** 品牌 */
	private Long		brandId;
	private String		brandName;
	/***/
	/** 单位 */
	private Long		unitId;
	private String		unitName;
	/** 材料数量 */
	private BigDecimal	amount;
	/** 单价 */
	private BigDecimal	price;
	/** 默认仓库 */
	private Long		warehouseId;
	private String		warehouseName;
	/** 默认供应商 */
	private Long		supplierId;
	private String		supplierName;
	/** 隐藏 */
	private Boolean		visible;

	public PaperOfMaterialExtGridRow() {
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public int getPaperHeight() {
		return paperHeight;
	}

	public void setPaperHeight(int paperHeight) {
		this.paperHeight = paperHeight;
	}

	public int getPaperWidth() {
		return paperWidth;
	}

	public void setPaperWidth(int paperWidth) {
		this.paperWidth = paperWidth;
	}

	public BigDecimal getPricePrePage() {
		return pricePrePage;
	}

	public void setPricePrePage(BigDecimal pricePrePage) {
		this.pricePrePage = pricePrePage;
	}

	public int getPaperWeight() {
		return paperWeight;
	}

	public void setPaperWeight(int paperWeight) {
		this.paperWeight = paperWeight;
	}

	public BigDecimal getTonnePrice() {
		return tonnePrice;
	}

	public void setTonnePrice(BigDecimal tonnePrice) {
		this.tonnePrice = tonnePrice;
	}

	public BigDecimal getSquareMetrePrice() {
		return squareMetrePrice;
	}

	public void setSquareMetrePrice(BigDecimal squareMetrePrice) {
		this.squareMetrePrice = squareMetrePrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Long getMaterialCategoryId() {
		return materialCategoryId;
	}

	public void setMaterialCategoryId(Long materialCategoryId) {
		this.materialCategoryId = materialCategoryId;
	}

	public String getMaterialCategoryName() {
		return materialCategoryName;
	}

	public void setMaterialCategoryName(String materialCategoryName) {
		this.materialCategoryName = materialCategoryName;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

}
