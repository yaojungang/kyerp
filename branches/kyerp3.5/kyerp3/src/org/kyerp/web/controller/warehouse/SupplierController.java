package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.common.view.ExtTreeNode;
import org.kyerp.domain.common.view.ExtTreeRecursion;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.Supplier;
import org.kyerp.domain.warehouse.SupplierType;
import org.kyerp.service.warehouse.ISupplierService;
import org.kyerp.service.warehouse.ISupplierTypeService;
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
public class SupplierController extends BaseController{
	@Autowired
	ISupplierService		supplierService;
	@Autowired
	ISupplierTypeService	supplierTypeService;

	@RequestMapping("/warehouse/Supplier/jsonList.html")
	public String list(Model model, Integer start, Integer limit, Long typeId, String query) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		// build where jpql
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" 1=?").append((queryParams.size() + 1));
		queryParams.add(1);
		// set parent id
		if(null != typeId) {
			wherejpql.append(" and o.supplierType.id=?").append(queryParams.size() + 1);
			queryParams.add(typeId);
		}
		// set query
		if(null != query && !query.equals("")) {
			wherejpql.append(" and (o.paperName like ?").append(queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// name
			wherejpql.append(" or o.name like ?").append(queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");

			wherejpql.append(" or o.serialNumber like ?").append(queryParams.size() + 1).append(")");
			queryParams.add("%" + query.trim() + "%");
		}
		// logger.info(wherejpql.toString() + "  -  " + queryParams.toArray());
		QueryResult<Supplier> queryResult = supplierService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);

		List<SupplierExtGridRow> rows = new ArrayList<SupplierExtGridRow>();
		for (Supplier o : queryResult.getResultlist()) {
			SupplierExtGridRow n = new SupplierExtGridRow();
			n.setId(o.getId());
			/** 分类 */
			if(null != o.getSupplierType()) {
				n.setTypeId(o.getSupplierType().getId());
				n.setTypeName(o.getSupplierType().getName());

			}
			/** 供应商简称 **/
			n.setName(o.getName());
			/** 供应商简拼 **/
			n.setNameSpell(o.getNameSpell());
			/** 供应商全称 **/
			n.setFullName(o.getFullName());
			/** 是否可见 **/
			n.setVisible(o.getVisible());
			/** 是否列入合格供方名录 **/
			n.setQualified(o.getQualified());
			/** 建立时间 */
			n.setCreateTime(DateFormatUtils.format(o.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			/** 修改时间 */
			if(null != o.getUpdateTime()) {
				n.setUpdateTime(DateFormatUtils.format(o.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
			}
			/** 供应商编码 */
			n.setSerialNumber(o.getSerialNumber());

			/** 备注 */
			n.setRemark(o.getRemark());
			/** 地址 */
			n.setAddress(o.getAddress());
			/** 邮编 */
			n.setPostcode(o.getPostcode());
			/** 电话 */
			n.setPhone(o.getPhone());
			/** 传真 */
			n.setFax(o.getFax());
			/** 网站 */
			n.setWww(o.getWww());
			/** 邮箱 */
			n.setEmail(o.getEmail());
			/** 助记码 */
			n.setHelpCode(o.getHelpCode());
			/** 应付款 */
			n.setPayCost(o.getPayCost());
			/** logo图片路径 如:/images/brand/2008/12/12/ooo.gif" **/
			n.setLogopath(o.getLogopath());
			rows.add(n);
		}
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/Supplier/jsonSave.html")
	public String save(SupplierExtGridRow o, ModelMap model) {
		Supplier n = new Supplier();
		if(null != o.getId() && o.getId() > 0) {
			n = supplierService.find(o.getId());
		}
		/** 分类 */
		if(o.getTypeId() > 0) {
			n.setSupplierType(supplierTypeService.find(o.getTypeId()));

		}
		/** 供应商简称 **/
		n.setName(o.getName());
		/** 供应商简拼 **/
		n.setNameSpell(o.getNameSpell());
		/** 供应商全称 **/
		n.setFullName(o.getFullName());
		/** 是否可见 **/
		n.setVisible(o.getVisible());
		/** 是否列入合格供方名录 **/
		n.setQualified(o.getQualified());
		/** 供应商编码 */
		n.setSerialNumber(o.getSerialNumber());
		/** 备注 */
		n.setRemark(o.getRemark());
		/** 地址 */
		n.setAddress(o.getAddress());
		/** 邮编 */
		n.setPostcode(o.getPostcode());
		/** 电话 */
		n.setPhone(o.getPhone());
		/** 传真 */
		n.setFax(o.getFax());
		/** 网站 */
		n.setWww(o.getWww());
		/** 邮箱 */
		n.setEmail(o.getEmail());
		/** 助记码 */
		n.setHelpCode(o.getHelpCode());
		/** 应付款 */
		n.setPayCost(o.getPayCost());
		/** logo图片路径 如:/images/brand/2008/12/12/ooo.gif" **/
		n.setLogopath(o.getLogopath());
		if(null != o.getId() && o.getId() > 0) {
			supplierService.update(n);
		} else {
			supplierService.save(n);
		}
		long id = n.getId() > 0 ? n.getId() : supplierService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/Supplier/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		supplierService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}

	// 供应商树
	/**
	 * 返回供应商树
	 * *
	 */
	@RequestMapping("/warehouse/Supplier/jsonTree.html")
	public String tree(Model model) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<SupplierType> typeQueryResult = supplierTypeService.getScrollData(orderby);

		List<ExtTreeNode> extTreeList = new ArrayList<ExtTreeNode>();
		if(typeQueryResult.getResultlist().size() == 0) {
			SupplierType supplierType = new SupplierType();
			supplierType.setName("供应商分类");
			supplierTypeService.save(supplierType);
			model.addAttribute("jsonText", "[{id:'1',text:'供应商分类',leaf:true}]");
		} else {
			for (SupplierType supplierType : typeQueryResult.getResultlist()) {
				ExtTreeNode node = new ExtTreeNode();
				node.setId(String.valueOf("type" + supplierType.getId()));
				node.setText(supplierType.getName());
				if(null != supplierType.getParentSupplierType() && supplierType.getParentSupplierType().getId() > 0) {
					node.setParentId(String.valueOf("type" + supplierType.getParentSupplierType().getId()));
				}
				if(supplierType.getId() == 1) {
					node.setExpanded(true);
				} else {
					node.setExpanded(false);
				}
				extTreeList.add(node);
			}

			QueryResult<Supplier> objQueryResult = supplierService.getScrollData(orderby);
			for (Supplier supplier : objQueryResult.getResultlist()) {
				ExtTreeNode node = new ExtTreeNode();
				node.setId(String.valueOf(supplier.getId()));
				node.setText(supplier.getName());
				node.setParentId(String.valueOf("type" + supplier.getSupplierType().getId()));
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
}
