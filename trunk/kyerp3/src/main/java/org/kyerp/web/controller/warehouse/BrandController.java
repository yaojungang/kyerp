package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.common.view.ExtTreeNode;
import org.kyerp.domain.common.view.ExtTreeRecursion;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.Brand;
import org.kyerp.service.warehouse.IBrandService;
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
public class BrandController extends BaseController{
	@Autowired
	IBrandService	brandService;

	@RequestMapping("/warehouse/Brand/jsonList.html")
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
			wherejpql.append(" and parentBrand.id=?").append(queryParams.size() + 1);
			queryParams.add(parentId);
		}
		QueryResult<Brand> queryResult = brandService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);
		List<BrandExtGridRow> rows = new ArrayList<BrandExtGridRow>();
		for (Brand o : queryResult.getResultlist()) {
			BrandExtGridRow n = new BrandExtGridRow();
			n.setId(o.getId());
			n.setName(o.getName());
			n.setCreateTime(DateFormatUtils.format(o.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			/** 修改时间 */
			if(null != o.getUpdateTime()) {
				n.setUpdateTime(DateFormatUtils.format(o.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
			}
			/** 申请单号 */
			n.setSerialNumber(o.getSerialNumber());
			n.setNote(o.getNote());
			/** 父类 */
			if(null != o.getParentBrand()) {
				n.setParentBrandId(o.getParentBrand().getId());
				n.setParentBrandName(o.getParentBrand().getName());
			} else {
				n.setParentBrandId(0);
				n.setParentBrandName("顶级分类");
			}
			rows.add(n);
		}
		;
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@RequestMapping("/warehouse/Brand/jsonTree.html")
	public String tree(Model model) throws Exception {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<Brand> queryResult = brandService.getScrollData(orderby);
		List<ExtTreeNode> extTreeList = new ArrayList<ExtTreeNode>();
		if(queryResult.getResultlist().size() == 0) {
			Brand brand = new Brand();
			brand.setName("品牌");
			brandService.save(brand);
			model.addAttribute("jsonText", "[{id:1,text:'品牌',leaf:true}]");
		} else {
			for (Brand d : queryResult.getResultlist()) {
				ExtTreeNode node = new ExtTreeNode();
				node.setId(String.valueOf(d.getId()));
				node.setText(d.getName());
				if(null != d.getParentBrand() && d.getParentBrand().getId() > 0) {
					node.setParentId(String.valueOf(d.getParentBrand().getId()));
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
	@RequestMapping("/warehouse/Brand/jsonSave.html")
	public String save(BrandExtGridRow brandRow, ModelMap model) throws Exception {
		Brand brand = new Brand();
		if(null != brandRow.getId() && brandRow.getId() > 0) {
			brand = brandService.find(brandRow.getId());
		}
		brand.setName(brandRow.getName());
		// 设置父类
		if(brandRow.getParentBrandId() != 0) {
			brand.setParentBrand(brandService.find(brandRow.getParentBrandId()));
		}
		// 设置note
		if(null != brandRow.getNote()) {
			brand.setNote(brandRow.getNote());
		}
		// 设置序号
		if(null != brandRow.getSerialNumber()) {
			brand.setSerialNumber(brandRow.getSerialNumber());
		}
		if(null != brandRow.getId() && brandRow.getId() > 0) {
			brandService.update(brand);
		} else {
			brandService.save(brand);
		}
		long id = brand.getId() > 0 ? brand.getId() : brandService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/Brand/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		brandService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}
}
