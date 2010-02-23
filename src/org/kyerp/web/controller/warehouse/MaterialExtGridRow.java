package org.kyerp.web.controller.warehouse;

/**
 * @author y109 2010-2-2下午05:58:53
 */
public class MaterialExtGridRow {
	private Long	id;
	/** 编号 **/
	private String	serialNumber;
	/** 名称 **/
	private String	name;
	/** 规格 **/
	private String	specification;
	/** 类别 */
	private Long	materialCategoryId;
	private String	materialCategoryName;
	/** 品牌 */
	private Long	brandId;
	private String	brandName;
	/***/
	/** 单位 */
	private Long	unitId;
	private String	unitName;
	/** 材料数量 */
	private double	amount;
	/** 单价 */
	private double	price;
	/** 物料明细 **/
	private String	batchIds;
	private String	batchNumbers;
	/** 默认仓库 */
	private Long	warehouseId;
	private String	warehouseName;
	/** 默认供应商 */
	private Long	supplierId;
	private String	supplierName;
	/** 隐藏 */
	private Boolean	visible;

	MaterialExtGridRow() {
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBatchIds() {
		return batchIds;
	}

	public void setBatchIds(String batchIds) {
		this.batchIds = batchIds;
	}

	public String getBatchNumbers() {
		return batchNumbers;
	}

	public void setBatchNumbers(String batchNumbers) {
		this.batchNumbers = batchNumbers;
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