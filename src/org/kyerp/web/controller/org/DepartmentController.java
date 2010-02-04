package org.kyerp.web.controller.org;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.kyerp.domain.base.views.ExtGridList;
import org.kyerp.domain.base.views.ExtTreeNode;
import org.kyerp.domain.base.views.ExtTreeRecursion;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.org.Department;
import org.kyerp.service.org.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 部门管理模块定义
 * 
 * @author y109 2009-12-31上午09:52:00
 */
@Controller
public class DepartmentController {
	@Autowired
	IDepartmentService	departmentService;

	@RequestMapping("/org/Department/jsonTree.html")
	public String tree(Model model) {

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<Department> queryResult = departmentService
				.getScrollData(orderby);
		List<ExtTreeNode> extTreeList = new ArrayList<ExtTreeNode>();
		for (Department d : queryResult.getResultlist()) {
			ExtTreeNode node = new ExtTreeNode();
			node.setId(new Integer(d.getId().toString()));
			node.setText(d.getName());
			if (null != d.getParentDepartment()
					&& d.getParentDepartment().getId() > 0) {
				node.setParentId(new Integer(d.getParentDepartment().getId()
						.toString()));
			}
			node.setExpanded(true);
			extTreeList.add(node);
		}
		ExtTreeRecursion r = new ExtTreeRecursion();
		r.recursionFn(extTreeList, extTreeList.get(0));

		model
				.addAttribute("jsonText", r.modifyStr(r.getReturnStr()
						.toString()));
		System.out.println("jsonText"
				+ r.modifyStr(r.getReturnStr().toString()));
		return "share/jsonTextView";
	}

	@RequestMapping("/org/Department/jsonList.html")
	public String list(Model model) {

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<Department> queryResult = departmentService
				.getScrollData(orderby);
		List<DepartmentGridRow> rows = new ArrayList<DepartmentGridRow>();
		for (Department d : queryResult.getResultlist()) {
			DepartmentGridRow r = new DepartmentGridRow();
			r.setId(d.getId());
			r.setName(d.getName());
			rows.add(r);
		}
		ExtGridList<DepartmentGridRow> departmentGrid = new ExtGridList<DepartmentGridRow>();
		departmentGrid.setRows(rows);
		JSONObject jsonObject = JSONObject.fromObject(departmentGrid);

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

	@RequestMapping("/org/Department/jsonTreeInsert.html")
	public String insert(Long parentId, Department department, ModelMap model) {
		if (null != department.getName()) {
			department.setParentDepartment(departmentService.find(parentId));
			departmentService.save(department);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		long id = departmentService.findLast().getId();
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

	@RequestMapping("/org/Department/jsonUpdate.html")
	public String update(Department department, ModelMap model) {
		if (department.getId() > 0) {
			Department d = departmentService.find(department.getId());
			d.setName(department.getName());
			departmentService.update(d);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		long id = department.getId() > 0 ? department.getId()
				: departmentService.findLast().getId();
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

	@RequestMapping("/org/Department/jsonTreeDelete.html")
	public String delete(ModelMap model, Long[] id) {
		departmentService.delete((Serializable[]) id);
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
