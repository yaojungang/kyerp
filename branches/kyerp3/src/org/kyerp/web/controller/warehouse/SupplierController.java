package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.Supplier;
import org.kyerp.service.warehouse.ISupplierService;
import org.kyerp.service.warehouse.ISupplierTypeService;
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
public class SupplierController {
	@Autowired
	ISupplierService		supplierService;
	@Autowired
	ISupplierTypeService	supplierTypeService;

	@RequestMapping("/warehouse/Supplier/jsonList.html")
	public String list(Model model, Integer start, Integer limit, Long typeId,
			String query) {
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
		if (null != typeId) {
			wherejpql.append(" and o.supplierType.id=?").append(
					queryParams.size() + 1);
			queryParams.add(typeId);
		}
		// set query
		if (null != query && !query.equals("")) {
			wherejpql.append(" and (o.fullName like ?").append(
					queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// name
			wherejpql.append(" or o.name like ?")
					.append(queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// nameSpell
			wherejpql.append(" or o.nameSpell like ?").append(
					queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// remark
			wherejpql.append(" or o.remark like ?").append(
					queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// address
			wherejpql.append(" or o.address like ?").append(
					queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// phone
			wherejpql.append(" or o.phone like ?").append(
					queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");

			wherejpql.append(" or o.serialNumber like ?").append(
					queryParams.size() + 1).append(")");
			queryParams.add("%" + query.trim() + "%");
		}
		System.out
				.print(wherejpql.toString() + "  -  " + queryParams.toArray());
		QueryResult<Supplier> queryResult = supplierService.getScrollData(
				start, limit, wherejpql.toString(), queryParams.toArray(),
				orderby);

		List<SupplierExtGridRow> rows = new ArrayList<SupplierExtGridRow>();
		for (Supplier o : queryResult.getResultlist()) {
			SupplierExtGridRow n = new SupplierExtGridRow();
			n.setId(o.getId());
			/** 分类 */
			if (null != o.getSupplierType()) {
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
			n.setCreateTime(DateFormatUtils.format(o.getCreateTime(),
					"yyyy-MM-dd HH:mm:ss"));
			/** 修改时间 */
			if (null != o.getUpdateTime()) {
				n.setUpdateTime(DateFormatUtils.format(o.getUpdateTime(),
						"yyyy-MM-dd HH:mm:ss"));
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
		if (null != o.getId() && o.getId() > 0) {
			n = supplierService.find(o.getId());
		}
		/** 分类 */
		if (o.getTypeId() > 0) {
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
		if (null != o.getId() && o.getId() > 0) {
			supplierService.update(n);
		} else {
			supplierService.save(n);
		}
		long id = n.getId() > 0 ? n.getId() : supplierService.findLast()
				.getId();
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
}
