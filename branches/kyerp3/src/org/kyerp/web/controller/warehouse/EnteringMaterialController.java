package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.kyerp.domain.base.views.ExtGridList;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.EnteringMaterial;
import org.kyerp.domain.warehouse.MaterialBatch;
import org.kyerp.service.warehouse.IEnteringMaterialService;
import org.kyerp.service.warehouse.IMaterialBatchService;
import org.kyerp.service.warehouse.IMaterialService;
import org.kyerp.service.warehouse.ISupplierService;
import org.kyerp.service.warehouse.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class EnteringMaterialController {
	@Autowired
	IEnteringMaterialService	enteringMaterialService;
	@Autowired
	IMaterialService			materialService;
	@Autowired
	IWarehouseService			warehouseService;
	@Autowired
	IMaterialBatchService		materialBatchService;
	@Autowired
	ISupplierService			supplierService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping("/warehouse/EnteringMaterial/jsonList.html")
	public String list(Model model, Integer start, Integer limit) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<EnteringMaterial> queryResult = enteringMaterialService
				.getScrollData(start, limit, orderby);
		List<EnteringMaterialExtGridRow> rows = new ArrayList<EnteringMaterialExtGridRow>();
		for (EnteringMaterial o : queryResult.getResultlist()) {
			EnteringMaterialExtGridRow n = new EnteringMaterialExtGridRow();
			n.setId(o.getId());
			n.setSerialNumber(o.getSerialNumber());
			n.setCreateTime(DateFormatUtils.format(o.getCreateTime(),
					"yyyy-MM-dd HH:mm:ss"));
			n.setEnteringTime(DateFormatUtils.format(o.getEnteringTime(),
					"yyyy-MM-dd"));
			if (null != o.getSupplier()) {
				n.setSupplierId(o.getSupplier().getId());
				n.setSupplierName(o.getSupplier().getName());
			}
			if (null != o.getKeeper()) {
				n.setKeeperId(o.getKeeper().getId());
				n.setKeeperName(o.getKeeper().getName());
			}
			if (null != o.getOperator()) {
				n.setOperatorId(o.getOperator().getId());
				n.setOperatorName(o.getOperator().getName());
			}
			if (null != o.getBatchs() && o.getBatchs().size() > 0) {
				StringBuffer mbIds = new StringBuffer();
				StringBuffer mbNames = new StringBuffer();
				for (MaterialBatch mBatch : o.getBatchs()) {
					mbIds.append(mBatch.getMaterial().getId()).append(",");
					mbNames.append(mBatch.getMaterial().getName()).append(",");
				}
				mbIds.deleteCharAt(mbIds.length() - 1);
				mbNames.deleteCharAt(mbNames.length() - 1);
				n.setBatchIds(mbIds.toString());
				n.setBatchNames(mbNames.toString());
				// 设置optItems
				List<EnteringMaterialOpeItemExtGridRow> opeRows = new ArrayList<EnteringMaterialOpeItemExtGridRow>();
				for (MaterialBatch mb : o.getBatchs()) {
					EnteringMaterialOpeItemExtGridRow opeRow = new EnteringMaterialOpeItemExtGridRow();
					opeRow.setId(mb.getId());
					opeRow.setMaterialId(mb.getMaterial().getId());
					opeRow.setMaterialName(mb.getMaterial().getName());
					opeRow.setAmount(mb.getAmount());
					opeRow.setBatchNumber(mb.getBatchNumber());
					opeRow.setMoney(new Float(mb.getMoney().toString()));
					opeRow.setPrice(new Float(mb.getPrice().toString()));
					opeRow.setRemark(mb.getRemark());
					opeRow.setUnitId(mb.getUnit().getId());
					opeRow.setUnitName(mb.getUnit().getName());
					opeRow.setWarehouseId(mb.getWarehouse().getId());
					opeRow.setWarehouseName(mb.getWarehouse().getName());

					opeRows.add(opeRow);
				}
				JSONArray opeItems = new JSONArray();
				opeItems = JSONArray.fromObject(opeRows);
				n.setOpeItems(opeItems.toString());
			}

			rows.add(n);
		}
		ExtGridList<EnteringMaterialExtGridRow> mGrid = new ExtGridList<EnteringMaterialExtGridRow>();
		mGrid.setStart(start);
		mGrid.setLimit(limit);
		mGrid.setTotalProperty(queryResult.getTotalrecord());
		mGrid.setRows(rows);
		JSONObject jsonObject = JSONObject.fromObject(mGrid);

		String text = "";

		try {
			text = jsonObject.toString();
			System.out.println(text);
		} catch (Exception e) {
			text = "";
		}
		model.addAttribute("jsonText", text);
		return "share/jsonTextView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/EnteringMaterial/jsonSave.html")
	public String save(EnteringMaterialExtGridRow row, ModelMap model)
			throws ParseException {

		EnteringMaterial enteringMaterial = new EnteringMaterial();
		if (null != row.getId() && row.getId() > 0) {
			enteringMaterial = enteringMaterialService.find(row.getId());
		}
		if (null != row.getSerialNumber()) {
			enteringMaterial.setSerialNumber(row.getSerialNumber());
		}
		if (null != row.getEnteringTime()) {
			Date enDate = DateUtils.parseDate(row.getEnteringTime(),
					new String[] { "yyyy-MM-dd" });
			enteringMaterial.setEnteringTime(enDate);
		}

		if (null != row.getSupplierId()) {
			enteringMaterial.setSupplier(supplierService.find(row
					.getSupplierId()));
		}
		if (null != row.getId() && row.getId() > 0) {
			enteringMaterialService.update(enteringMaterial);
		} else {
			enteringMaterialService.saveEnteringMaterial(enteringMaterial);
		}
		// 保存物料批次信息
		if (null != row.getOpeItems() && row.getOpeItems().length() > 0) {
			List<MaterialBatch> mbs = new ArrayList<MaterialBatch>();
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonArray = JSONArray.fromObject(row.getOpeItems());
			for (int i = 0; i < jsonArray.size(); i++) {
				MaterialBatch b = new MaterialBatch();
				jsonObject = jsonArray.getJSONObject(i);
				String idString = jsonObject.getString("id");
				if (null != idString && idString.length() > 0) {
					b = materialBatchService.find(new Long(idString));
				}
				b.setMaterial(materialService.find(jsonObject
						.getLong("materialId")));
				b.setPrice(new BigDecimal(jsonObject.getString("price")));
				b.setAmount(new Float(jsonObject.getString("amount")));
				b
						.setMoney(new BigDecimal(jsonObject.getString("price"))
								.multiply(new BigDecimal(jsonObject
										.getString("amount"))));
				b.setWarehouse(warehouseService.find(jsonObject
						.getLong("warehouseId")));
				// 供应商为入库单的供应商
				b.setSupplier(supplierService.find(row.getSupplierId()));
				// 单位为默认单位
				b.setUnit(materialService
						.find(jsonObject.getLong("materialId")).getUnit());
				if (null != jsonObject.getString("batchNumber")) {
					b.setBatchNumber(jsonObject.getString("batchNumber"));
				}
				if (null != jsonObject.getString("remark")) {
					b.setRemark(jsonObject.getString("remark"));
				}
				b.setEnteringMaterial(enteringMaterial);

				if (null != idString && idString.length() > 0) {
					materialBatchService.update(b);
				} else {
					materialBatchService.save(b);
				}
				mbs.add(b);
			}
			enteringMaterial.setBatchs(mbs);
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		long id = enteringMaterial.getId() > 0 ? enteringMaterial.getId()
				: enteringMaterialService.findLast().getId();
		jsonObject.put("id", id);
		String text = "";
		try {
			text = jsonObject.toString();
			System.out.println(text);
		} catch (Exception e) {
			text = "";
		}
		model.addAttribute("jsonText", text);
		return "share/jsonTextView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/EnteringMaterial/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		enteringMaterialService.delete((Serializable[]) ids);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		String text = "";
		try {
			text = jsonObject.toString();
			System.out.println(text);
		} catch (Exception e) {
			text = "";
		}
		model.addAttribute("jsonText", text);
		return "share/jsonTextView";
	}
}
