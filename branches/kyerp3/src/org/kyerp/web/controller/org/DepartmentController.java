package org.kyerp.web.controller.org;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.base.views.ExtTreeNode;
import org.kyerp.domain.base.views.ExtTreeRecursion;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.org.Department;
import org.kyerp.service.org.IDepartmentService;
import org.kyerp.web.controller.BaseController;
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
public class DepartmentController extends BaseController{
	@Autowired
	IDepartmentService	departmentService;

	@RequestMapping("/org/Department/jsonList.html")
	public String list(Long parentId, Integer start, Integer limit, Model model) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;
		parentId = null == parentId ? 1 : parentId;
		// build order by
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		// build where jpql
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" 1=?").append((queryParams.size() + 1));
		queryParams.add(1);
		// set parent id
		if(null != parentId) {
			wherejpql.append(" and parentDepartment.id=?").append(queryParams.size() + 1);
			queryParams.add(parentId);
		}
		QueryResult<Department> queryResult = departmentService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);
		List<DepartmentExtGridRow> rows = new ArrayList<DepartmentExtGridRow>();
		for (Department o : queryResult.getResultlist()) {
			DepartmentExtGridRow n = new DepartmentExtGridRow();
			n.setId(o.getId());
			n.setName(o.getName());
			n.setCreateTime(DateFormatUtils.format(o.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			/** 修改时间 */
			if(null != o.getUpdateTime()) {
				n.setUpdateTime(DateFormatUtils.format(o.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
			}
			/** 申请单号 */
			n.setSerialNumber(o.getSerialNumber());
			n.setRemark(o.getRemark());
			/** 父类 */
			if(null != o.getParentDepartment()) {
				n.setParentDepartmentId(o.getParentDepartment().getId());
				n.setParentDepartmentName(o.getParentDepartment().getName());
			} else {
				n.setParentDepartmentId(new Long(0));
				n.setParentDepartmentName("顶级分类");
			}
			rows.add(n);
		}
		;
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@RequestMapping("/org/Department/jsonTree.html")
	public String tree(Model model) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<Department> queryResult = departmentService.getScrollData(orderby);
		List<ExtTreeNode> extTreeList = new ArrayList<ExtTreeNode>();
		if(queryResult.getResultlist().size() == 0) {
			Department department = new Department();
			department.setName("品牌");
			departmentService.save(department);
			model.addAttribute("jsonText", "[{id:1,text:'品牌',leaf:true}]");
		} else {
			for (Department d : queryResult.getResultlist()) {
				ExtTreeNode node = new ExtTreeNode();
				node.setId(String.valueOf(d.getId()));
				node.setText(d.getName());
				if(null != d.getParentDepartment() && d.getParentDepartment().getId() > 0) {
					node.setParentId(String.valueOf(d.getParentDepartment().getId()));
				}
				if(d.getId() == 1) {
					node.setExpanded(true);
				} else {
					node.setExpanded(false);
				}
				extTreeList.add(node);
			}

			ExtTreeRecursion r = new ExtTreeRecursion();
			if(null != extTreeList && extTreeList.size() > 0) {
				r.recursionFn(extTreeList, extTreeList.get(0));
			}
			String strTreeString = r.modifyStr(r.getReturnStr().toString());

			model.addAttribute("jsonText", strTreeString);
		}
		return "share/jsonTextView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/org/Department/jsonSave.html")
	public String save(DepartmentExtGridRow departmentRow, ModelMap model) {
		Department department = new Department();
		if(null != departmentRow.getId() && departmentRow.getId() > 0) {
			department = departmentService.find(departmentRow.getId());
		}
		department.setName(departmentRow.getName());
		// 设置父类
		if(departmentRow.getParentDepartmentId() != 0) {
			department.setParentDepartment(departmentService.find(departmentRow.getParentDepartmentId()));
		}
		// 设置备注
		if(null != departmentRow.getRemark()) {
			department.setRemark(departmentRow.getRemark());
		}
		// 设置序号
		if(null != departmentRow.getSerialNumber()) {
			department.setSerialNumber(departmentRow.getSerialNumber());
		}
		if(null != departmentRow.getId() && departmentRow.getId() > 0) {
			departmentService.update(department);
		} else {
			departmentService.save(department);
		}
		long id = department.getId() > 0 ? department.getId() : departmentService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/org/Department/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		departmentService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}
}
