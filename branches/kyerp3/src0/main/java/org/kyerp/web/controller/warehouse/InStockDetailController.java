package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.InStockDetail;
import org.kyerp.service.warehouse.IInStockDetailService;
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
@RequestMapping("/warehouse/InStockDetail/")
public class InStockDetailController extends BaseController {
	@Autowired
	IInStockDetailService inStockDetailService;
	

	@RequestMapping("jsonList.html")
	public String list(Model model, Integer start, Integer limit,Long inStockId) throws Exception {
		try {
			start = null == start ? 0 : start;
			limit = null == limit ? 20 : limit;
			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
			orderby.put("id", "desc");
			// build where jpql
			StringBuffer wherejpql = new StringBuffer("");
			List<Object> queryParams = new ArrayList<Object>();
			wherejpql.append(" 1=?").append((queryParams.size() + 1));
			queryParams.add(1);
			// set parent id
			if (null != inStockId) {
				wherejpql.append(" and o.inStock.id=?").append(
						queryParams.size() + 1);
				queryParams.add(inStockId);
			}
			QueryResult<InStockDetail> queryResult = inStockDetailService.getScrollData(
					start, limit, wherejpql.toString(), queryParams.toArray(),
					orderby);
			List<InStockDetailExtGridRow> rows = new ArrayList<InStockDetailExtGridRow>();
			for (InStockDetail detail : queryResult.getResultlist()) {
				InStockDetailExtGridRow row = new InStockDetailExtGridRow();
				/** id */
				row.setId(detail.getId());
				/** 时间 */
				row.setCreateTime(DateFormatUtils.format(
						detail.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
				/** 修改时间 */
				if (null != detail.getUpdateTime()) {
					row.setUpdateTime(DateFormatUtils.format(
							detail.getUpdateTime(),
							"yyyy-MM-dd HH:mm:ss"));
				}
				// 所有者
				if (null != detail.getOwner()) {
					row.setOwnerId(detail.getOwner().getId());
					row.setOwnerName(detail.getOwner().getName());
				}
				/** 采购申请单 */
				if (null != detail.getInStock()) {
					row.setInStockId(detail.getInStock().getId());
					row.setInStockSerialNumber(detail.getInStock()
							.getSerialNumber());
				}
				/** 物料 */
				if (null != detail.getMaterial()) {
					row.setMaterialId(detail.getMaterial().getId());
					row.setMaterialName(detail.getMaterial().getName());
				}
				// 批次号
				row.setBatchNumber(detail.getBatchNumber());
				/** 仓库 */
				if (null != detail.getWarehouse()) {
					row.setWarehouseId(detail.getWarehouse().getId());
					row.setWarehouseName(detail.getWarehouse()
							.getName());
				}
				/** 单位 */
				if (null != detail.getUnit()) {
					row.setUnitId(detail.getUnit().getId());
					row.setUnitName(detail.getUnit().getName());
				}
				/** 数量 */
				row.setBillCount(detail.getInStockCount());
				/** 金额 */
				row.setBillCost(detail.getBillCost());
				/** 价格 */
				if (null != detail.getPrice()) {
					row.setPrice(detail.getPrice());
				}
				/** 备注 */
				if (null != detail.getRemark()) {
					row.setRemark(detail.getRemark());
				}
				rows.add(row);
			}
			model.addAttribute("start", limit);
			model.addAttribute("limit", limit);
			model.addAttribute("totalProperty", queryResult.getTotalrecord());
			model.addAttribute("rows", rows);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("failure", true);
			model.addAttribute("message",
					"获取数据失败！<br />" + e.getLocalizedMessage());
		}
		return "jsonView";
	}


	@Secured({ "ROLE_ADMIN" })
	@RequestMapping("jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		try {
			inStockDetailService.delete((Serializable[]) ids);
			model.addAttribute("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("failure", true);
			model.addAttribute("message",
					"删除失败!<br />" + e.getLocalizedMessage());
		}
		return "jsonView";
	}

	/**
	 * 修改入库单的备注
	 */
	@RequestMapping("jsonSave.html")
	public String save(InStockDetailExtGridRow row, ModelMap model) {
		try {
			InStockDetail sd = null;
			if (null != row.getId() && row.getId() > 0) {
				sd = inStockDetailService.find(row.getId());
				sd.setRemark(row.getRemark());
				inStockDetailService.update(sd);
				model.addAttribute("success", true);
				model.addAttribute("message", "修改入库单的备注！");
			} else {
				model.addAttribute("failure", true);
				model.addAttribute("message", "入库单的ID不能为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("failure", true);
			model.addAttribute("message",
					"修改入库单的备注失败！<br />" + e.getLocalizedMessage());
		}
		return "jsonView";
	}
}
