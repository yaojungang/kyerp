package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.kyerp.domain.base.views.ExtGridList;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.EnteringMaterial;
import org.kyerp.domain.warehouse.MaterialBatch;
import org.kyerp.service.warehouse.IEnteringMaterialService;
import org.kyerp.service.warehouse.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class EnteringMaterialController {
	@Autowired
	IEnteringMaterialService	enteringMaterialService;
	@Autowired
	IWarehouseService			warehouseService;

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
			n.setEnteringTime(o.getEnteringTime());
			if (null != o.getWarehouse()) {
				n.setWarehouseId(o.getWarehouse().getId());
				n.setWarehouseName(o.getWarehouse().getName());
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
				for (MaterialBatch mBatch : o.getBatchs()) {
					mbIds.append(mBatch.getBatchNumber()).append(",");
				}
				mbIds.deleteCharAt(mbIds.length() - 1);
				n.setBatchIds(mbIds.toString());
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
	public String save(EnteringMaterialExtGridRow row, ModelMap model) {
		EnteringMaterial enteringMaterial = new EnteringMaterial();
		if (null != row.getId() && row.getId() > 0) {
			enteringMaterial = enteringMaterialService.find(row.getId());
		}
		enteringMaterial.setSerialNumber(row.getSerialNumber());
		if (null != row.getWarehouseId()) {
			enteringMaterial.setWarehouse(warehouseService.find(row
					.getWarehouseId()));
		}
		if (null != row.getId() && row.getId() > 0) {
			enteringMaterialService.update(enteringMaterial);
		} else {
			enteringMaterialService.saveEnteringMaterial(enteringMaterial);
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
