package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.Warehouse;
import org.kyerp.service.warehouse.IWarehouseService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class WarehouseController {
	@Resource(name = "warehouseService")
	IWarehouseService	warehouseService;

	@RequestMapping("/warehouse/Warehouse/jsonList.html")
	public String list(Model model, Integer start, Integer limit) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<Warehouse> queryResult = warehouseService.getScrollData(
				start, limit, orderby);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("start", start);
		jsonObject.put("limit", limit);
		jsonObject.put("totalProperty", queryResult.getTotalrecord());
		jsonObject.put("rows", queryResult.getResultlist());

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
	@RequestMapping("/warehouse/Warehouse/jsonSave.html")
	public String save(WarehouseExtGridRow warehouseRow, ModelMap model) {
		Warehouse warehouse = new Warehouse();
		if (null != warehouseRow.getId() && warehouseRow.getId() > 0) {
			warehouse = warehouseService.find(warehouseRow.getId());
		}
		warehouse.setName(warehouseRow.getName());
		warehouse.setSerialNumber(warehouseRow.getSerialNumber());
		if (null != warehouseRow.getId() && warehouseRow.getId() > 0) {
			warehouseService.update(warehouse);
		} else {
			warehouseService.save(warehouse);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		long id = warehouse.getId() > 0 ? warehouse.getId() : warehouseService
				.findLast().getId();
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
	@RequestMapping("/warehouse/Warehouse/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		warehouseService.delete((Serializable[]) ids);
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
