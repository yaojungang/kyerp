package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.BillStatus;
import org.kyerp.domain.warehouse.InStock;
import org.kyerp.domain.warehouse.InStockDetail;
import org.kyerp.service.org.IEmployeeService;
import org.kyerp.service.security.IUserService;
import org.kyerp.service.warehouse.IInOutTypeService;
import org.kyerp.service.warehouse.IInStockDetailService;
import org.kyerp.service.warehouse.IInStockService;
import org.kyerp.service.warehouse.IInventoryOwnerService;
import org.kyerp.service.warehouse.IMaterialService;
import org.kyerp.service.warehouse.IStockService;
import org.kyerp.service.warehouse.ISupplierService;
import org.kyerp.service.warehouse.IWarehouseService;
import org.kyerp.utils.WebUtil;
import org.kyerp.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
@RequestMapping("/warehouse/InStock/")
public class InStockController extends BaseController {
	@Autowired
	IInStockService inStockService;
	@Autowired
	IInStockDetailService inStockDetailService;
	@Autowired
	IStockService stockService;
	@Autowired
	IMaterialService materialService;
	@Autowired
	ISupplierService supplierService;
	@Autowired
	IUserService userService;
	@Autowired
	IWarehouseService warehouseService;
	@Autowired
	IInOutTypeService inOutTypeService;
	@Autowired
	IEmployeeService employeeService;
	@Autowired
	IInventoryOwnerService inventoryOwnerService;

	@RequestMapping("jsonList.html")
	public String list(Model model, Integer start, Long inOutTypeId,
			Long supplierId, String query, Integer limit) throws Exception {
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
			if (null != supplierId) {
				wherejpql.append(" and o.supplier.id=?").append(
						queryParams.size() + 1);
				queryParams.add(supplierId);
			}
			// set inOutTypeId
			if (null != inOutTypeId) {
				wherejpql.append(" and o.inOutType.id=?").append(
						queryParams.size() + 1);
				queryParams.add(inOutTypeId);
			}
			// set query
			if (null != query && !query.equals("") && query.trim().length() > 0) {
				wherejpql.append(" and (o.serialNumber like ?").append(
						queryParams.size() + 1);
				queryParams.add("%" + query.trim() + "%");
				// material's serialNumber
				wherejpql.append(" or o.remark like ?")
						.append(queryParams.size() + 1).append(")");
				queryParams.add("%" + query.trim() + "%");
			}
			QueryResult<InStock> queryResult = inStockService.getScrollData(
					start, limit, wherejpql.toString(), queryParams.toArray(),
					orderby);
			List<InStockExtGridRow> rows = new ArrayList<InStockExtGridRow>();
			for (InStock o : queryResult.getResultlist()) {
				InStockExtGridRow n = new InStockExtGridRow();
				/** id */
				n.setId(o.getId());
				/** 时间 */
				n.setCreateTime(DateFormatUtils.format(o.getCreateTime(),
						"yyyy-MM-dd HH:mm:ss"));
				/** 修改时间 */
				if (null != o.getUpdateTime()) {
					n.setUpdateTime(DateFormatUtils.format(o.getUpdateTime(),
							"yyyy-MM-dd HH:mm:ss"));
				}
				/** 申请单号 */
				n.setSerialNumber(o.getSerialNumber());
				/** 收发类型 */
				if (null != o.getInOutType()) {
					n.setInOutTypeId(o.getInOutType().getId());
					n.setInOutTypeName(o.getInOutType().getName());
				}
				/** 供应商 */
				if (null != o.getSupplier()) {
					n.setSupplierId(o.getSupplier().getId());
					n.setSupplierName(o.getSupplier().getName());
				}
				/** 备注 */
				if (null != o.getRemark()) {
					n.setRemark(o.getRemark());
				}
				/** 总数量 */
				n.setBillCount(o.getBillCount());
				/** 总费用 */
				if (null != o.getBillCost()) {
					n.setBillCost(o.getBillCost());
				}
				/** 填单人 */
				if (null != o.getWriteUser()) {
					n.setWriteUserId(o.getWriteUser().getId());
					n.setWriteUserName(o.getWriteUser().getUsername());
				}
				if (null != o.getWriteEmployee()) {
					n.setWriteEmployeeId(o.getWriteEmployee().getId());
					n.setWriteEmployeeName(o.getWriteEmployee().getName());
				}
				/** 经手人 */
				if (null != o.getKeeper()) {
					n.setKeeperId(o.getKeeper().getId());
					n.setKeeperName(o.getKeeper().getName());
				}
				/** 审核人 */
				if (null != o.getCheckUser()) {
					n.setCheckUserId(o.getCheckUser().getId());
					n.setCheckUserName(o.getCheckUser().getUsername());
				}
				if (null != o.getCheckEmployee()) {
					n.setCheckEmployeeId(o.getCheckEmployee().getId());
					n.setCheckEmployeeName(o.getCheckEmployee().getName());
				}
				/** 填写时间 */
				if (null != o.getWriteDate()) {
					n.setWriteDate(DateFormatUtils.format(o.getWriteDate(),
							"yyyy-MM-dd HH:mm:ss"));
				}
				/** 审核时间 */
				if (null != o.getCheckDate()) {
					n.setCheckDate(DateFormatUtils.format(o.getCheckDate(),
							"yyyy-MM-dd HH:mm:ss"));
				}
				/** 单据状态 */
				if (null != o.getStatus()) {
					n.setStatusString(o.getStatus().getName());
				}
				try {
					/**
					 * 能否编辑 只有本人可以编辑
					 * */
					if ("0".equals(WebUtil.getCurrentUser().getId())
							|| (BillStatus.WRITING.equals(o.getStatus()) && o
									.getWriteUser()
									.getId()
									.toString()
									.equals(WebUtil.getCurrentUser().getId()
											.toString()))) {
						n.setEditAble("true");
					} else {
						n.setEditAble("false");
					}
				} catch (Exception e) {
					model.addAttribute("failure", true);
					model.addAttribute("message",
							"请重新登录！<br />" + e.getLocalizedMessage());
				}
				/** 入库日期 */
				if (null != o.getArriveDate()) {
					n.setArriveDate(DateFormatUtils.format(o.getArriveDate(),
							"yyyy-MM-dd"));
				}
				/** 明细 */
				if (null != o.getDetails() && o.getDetails().size() > 0) {
					List<InStockDetailExtGridRow> itemRows = new ArrayList<InStockDetailExtGridRow>();
					for (InStockDetail detail : o.getDetails()) {
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
						itemRows.add(row);
					}
					JSONArray rowArrayItems = new JSONArray();
					rowArrayItems = JSONArray.fromObject(itemRows);
					n.setDetails(rowArrayItems.toString());
				}
				rows.add(n);
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
	@RequestMapping("jsonSave.html")
	public String save(InStockExtGridRow row, ModelMap model) throws Exception {
		try {
			InStock inStock = null;
			if (null != row.getId() && row.getId() > 0) {
				inStock = inStockService.find(row.getId());
			} else {
				inStock = new InStock();
			}

			// 保存单号
			if (null != row.getSerialNumber()) {
				inStock.setSerialNumber(row.getSerialNumber());
			}
			// 保存收发类型
			if (null != row.getInOutTypeId()) {
				inStock.setInOutType(inOutTypeService.find(row.getInOutTypeId()));
			}
			// 保存填单日期
			if (null != row.getWriteDate()) {
				inStock.setWriteDate(DateUtils.parseDate(row.getWriteDate(),
						new String[] { "yyyy-MM-dd" }));
			}
			// 保存入库时间
			if (null != row.getArriveDate()) {
				inStock.setArriveDate(DateUtils.parseDate(row.getArriveDate(),
						new String[] { "yyyy-MM-dd" }));
			}
			// 保存经手人
			if (null != row.getKeeperId()) {
				inStock.setKeeper(employeeService.find(row.getKeeperId()));
			}
			// 供应商
			if (null != row.getSupplierId()) {
				inStock.setSupplier(supplierService.find(row.getSupplierId()));
			}
			// 保存备注
			if (null != row.getRemark()) {
				inStock.setRemark(row.getRemark());
			}
			// 保存物料批次信息
			if (null != row.getDetails() && row.getDetails().length() > 0) {
				List<InStockDetail> pods = new ArrayList<InStockDetail>();
				JSONArray jsonArray = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				jsonArray = JSONArray.fromObject(row.getDetails());
				for (int i = 0; i < jsonArray.size(); i++) {
					InStockDetail detail = new InStockDetail();
					String idString;
					try {
						jsonObject = jsonArray.getJSONObject(i);
						idString = jsonObject.getString("id");
						if (StringUtils.hasText(idString)) {
							detail = inStockDetailService.find(new Long(
									idString));
						}
						// 所有者
						detail.setOwner(inventoryOwnerService.find(jsonObject
								.getLong("ownerId")));
						// 物料
						detail.setMaterial(materialService.find(jsonObject
								.getLong("materialId")));
						// 批次号
						detail.setBatchNumber(jsonObject.getString(
								"batchNumber").toUpperCase());
						// 仓库
						detail.setWarehouse(warehouseService.find(jsonObject
								.getLong("warehouseId")));
						detail.setPrice(new BigDecimal(jsonObject
								.getString("price")));
						// 单位为物料单位
						detail.setUnit(materialService.find(
								jsonObject.getLong("materialId")).getUnit());
						// 数量
						detail.setInStockCount(new BigDecimal(jsonObject
								.getString("billCount")));
						// 备注
						if (null != jsonObject.getString("remark")) {
							detail.setRemark(jsonObject.getString("remark"));
						}
						detail.setInStock(inStock);
					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("failure", true);
						model.addAttribute("message", "数据绑定失败，请检查您输入的数据！<br />"
								+ e.getLocalizedMessage());
						return "jsonView";
					}
					pods.add(detail);
				}
				inStock.setDetails(pods);
			}
			if (null != inStock.getId()) {
				inStockService.update(inStock);
			} else {
				inStockService.save(inStock);
			}
			long id = inStock.getId() > 0 ? inStock.getId() : inStockService
					.findLast().getId();
			model.addAttribute("message", "保存成功!");
			model.addAttribute("success", true);
			model.addAttribute("id", id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("failure", true);
			model.addAttribute("message",
					"保存失败!<br />" + e.getLocalizedMessage());
		}
		return "jsonView";
	}

	@Secured({ "ROLE_ADMIN" })
	@RequestMapping("jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		try {
			inStockService.delete((Serializable[]) ids);
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
	 * 提交审核
	 * 
	 * @throws Exception
	 */
	@RequestMapping("jsonPostForCheck.html")
	public String postForCheck(ModelMap model, Long id) {
		try {
			InStock inStock = inStockService.find(id);
			inStock.setStatus(BillStatus.WAITING_FOR_CHECK);
			inStockService.update(inStock);
			model.addAttribute("success", true);
			model.addAttribute("message",
					"单据提交审核成功，您将不能修改这个单据，欲修改这个单据请先返回编制状态！");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("failure", true);
			model.addAttribute("message",
					"单据提交审核失败！<br />" + e.getLocalizedMessage());
		}
		return "jsonView";
	}

	/**
	 * 返回编制状态
	 */
	@RequestMapping("jsonReturnForEdit.html")
	public String returnForEdit(ModelMap model, Long id) {
		try {
			InStock inStock = inStockService.find(id);
			if (BillStatus.CHECKED == inStock.getStatus()) {
				model.addAttribute("failure", true);
				model.addAttribute("message", "单据已经审核过，不能再修改。");
			} else {
				inStock.setStatus(BillStatus.WRITING);
				inStockService.save(inStock);
				model.addAttribute("success", true);
				model.addAttribute("message", "单据返回编制状态，可以修改。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("failure", true);
			model.addAttribute("message",
					"单据返回编制状态失败！<br />" + e.getLocalizedMessage());
		}
		return "jsonView";
	}

	/**
	 * 审核单据
	 */
	@RequestMapping("jsonCheckBill.html")
	public String checkBill(ModelMap model, Long id) {
		try {
			inStockService.checkInStock(id);
			model.addAttribute("success", true);
			model.addAttribute("message", "单据审核成功！");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("failure", true);
			model.addAttribute("message", "审核单据时发生异常：" + e.getMessage());
		}
		return "jsonView";
	}

	/**
	 * 冲销单据
	 */
	@RequestMapping("jsonCongXiao.html")
	public String congXiao(ModelMap model, Long id) throws Exception {
		try {
			inStockService.congXiao(id);
			model.addAttribute("success", true);
			model.addAttribute("message", "冲销单据成功！");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("failure", true);
			model.addAttribute("message", "冲销单据失败！<br />" + e.getMessage());
		}
		return "jsonView";
	}

	/**
	 * 重设入库单的单号
	 * 
	 * @throws Exception
	 */
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping("resetSerialNumber.html")
	public String resetSerialNumber(ModelMap model) throws Exception {
		try {
			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
			orderby.put("serialNumber", "desc");
			List<InStock> list = inStockService.getScrollData(null, null,
					orderby).getResultlist();
			for (InStock inStock : list) {
				inStock.setSerialNumber(null);
				inStockService.save(inStock);
			}
			for (InStock inStock : list) {
				inStock.setSerialNumber(inStockService.nextSerialNumber());
				inStockService.save(inStock);
			}
			for (InStock inStock : list) {
				inStock.setSerialNumber(inStockService.nextSerialNumber());
				inStockService.save(inStock);
			}
			model.addAttribute("message", "重设入库单的单号成功!");
			model.addAttribute("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("failure", true);
			model.addAttribute("message", "重设入库单的单号失败！<br />" + e.getMessage());
		}
		return "jsonView";
	}
}
