package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.Supplier;
import org.kyerp.service.warehouse.ISupplierService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class SupplierController {
	@Resource(name = "supplierService")
	ISupplierService	supplierService;

	@RequestMapping("/warehouse/Supplier/jsonList.html")
	public String list(Model model, Integer start, Integer limit) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<Supplier> queryResult = supplierService.getScrollData(
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
	@RequestMapping("/warehouse/Supplier/jsonSave.html")
	public String save(SupplierExtGridRow supplierRow, ModelMap model) {
		Supplier supplier = new Supplier();
		if (null != supplierRow.getId() && supplierRow.getId() > 0) {
			supplier = supplierService.find(supplierRow.getId());
		}
		supplier.setName(supplierRow.getName());
		supplier.setNameSpell(supplierRow.getNameSpell());
		supplier.setFullName(supplierRow.getFullName());
		if (null != supplierRow.getId() && supplierRow.getId() > 0) {
			supplierService.update(supplier);
		} else {
			supplierService.save(supplier);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		long id = supplier.getId() > 0 ? supplier.getId() : supplierService
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
	@RequestMapping("/warehouse/Supplier/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		supplierService.delete((Serializable[]) ids);
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
