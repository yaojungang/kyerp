package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.kyerp.domain.base.views.ExtGridList;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.Unit;
import org.kyerp.service.warehouse.IUnitService;
import org.kyerp.web.controller.BaseController;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class UnitController extends BaseController {
	@Resource(name = "unitService")
	IUnitService	unitService;

	@RequestMapping("/warehouse/Unit/jsonList.html")
	public String list(Model model, Integer start, Integer limit) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<Unit> queryResult = unitService.getScrollData(start, limit,
				orderby);
		List<UnitExtGridRow> rows = new ArrayList<UnitExtGridRow>();
		for (Unit b : queryResult.getResultlist()) {
			UnitExtGridRow nb = new UnitExtGridRow();
			nb.setId(b.getId());
			nb.setName(b.getName());
			nb.setNameSpell(b.getNameSpell());
			nb.setSerialNumber(b.getSerialNumber());
			rows.add(nb);
		}
		ExtGridList<UnitExtGridRow> mGrid = new ExtGridList<UnitExtGridRow>();
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
	@RequestMapping("/warehouse/Unit/jsonSave.html")
	public String save(UnitExtGridRow unitRow, ModelMap model) {
		Unit unit = new Unit();
		if (null != unitRow.getId() && unitRow.getId() > 0) {
			unit = unitService.find(unitRow.getId());
		}
		unit.setName(unitRow.getName());
		unit.setNameSpell(unitRow.getNameSpell());
		if (null != unitRow.getId() && unitRow.getId() > 0) {
			unitService.update(unit);
		} else {
			unitService.save(unit);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		long id = unit.getId() > 0 ? unit.getId() : unitService.findLast()
				.getId();
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
	@RequestMapping("/warehouse/Unit/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		unitService.delete((Serializable[]) ids);
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
