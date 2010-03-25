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
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.BillStatus;
import org.kyerp.domain.warehouse.OutStock;
import org.kyerp.domain.warehouse.OutStockDetail;
import org.kyerp.service.org.IDepartmentService;
import org.kyerp.service.org.IEmployeeService;
import org.kyerp.service.security.IUserService;
import org.kyerp.service.warehouse.IInOutTypeService;
import org.kyerp.service.warehouse.IMaterialService;
import org.kyerp.service.warehouse.IOutStockDetailService;
import org.kyerp.service.warehouse.IOutStockService;
import org.kyerp.service.warehouse.ISupplierService;
import org.kyerp.service.warehouse.IWarehouseService;
import org.kyerp.utils.WebUtil;
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
public class OutStockController extends BaseController{
	@Autowired
	IOutStockService		outStockService;
	@Autowired
	IOutStockDetailService	outStockDetailService;
	@Autowired
	IMaterialService		materialService;
	@Autowired
	ISupplierService		supplierService;
	@Autowired
	IUserService			userService;
	@Autowired
	IWarehouseService		warehouseService;
	@Autowired
	IInOutTypeService		inOutTypeService;
	@Autowired
	IDepartmentService		departmentService;
	@Autowired
	IEmployeeService		employeeService;

	@RequestMapping("/warehouse/OutStock/jsonList.html")
	public String list(Model model, Integer start, Integer limit) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		QueryResult<OutStock> queryResult = outStockService.getScrollData(start, limit, orderby);
		List<OutStockExtGridRow> rows = new ArrayList<OutStockExtGridRow>();
		for (OutStock o : queryResult.getResultlist()) {
			OutStockExtGridRow n = new OutStockExtGridRow();
			/** id */
			n.setId(o.getId());
			/** 时间 */
			n.setCreateTime(DateFormatUtils.format(o.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			/** 修改时间 */
			if(null != o.getUpdateTime()) {
				n.setUpdateTime(DateFormatUtils.format(o.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
			}
			/** 申请单号 */
			n.setSerialNumber(o.getSerialNumber());
			/** 收发类型 */
			if(null != o.getInOutType()) {
				n.setInOutTypeId(o.getInOutType().getId());
				n.setInOutTypeName(o.getInOutType().getName());
			}
			/** 出库时间 */
			if(null != o.getOutDate()) {
				n.setOutDate(DateFormatUtils.format(o.getOutDate(), "yyyy-MM-dd"));
			}
			/**
			 * 领取部门、人员
			 */
			if(null != o.getReceiveDepartment()) {
				n.setReceiveDepartmentId(o.getReceiveDepartment().getId());
				n.setReceiveDepartmentName(o.getReceiveDepartment().getName());
			}
			if(null != o.getReceiveEmployee()) {
				n.setReceiveEmployeeId(o.getReceiveEmployee().getId());
				n.setReceiveEmployeeName(o.getReceiveEmployee().getName());
			}

			/** 备注 */
			if(null != o.getRemark()) {
				n.setRemark(o.getRemark());
			}
			/** 总数量 */
			n.setBillCount(o.getBillCount());
			/** 总费用 */
			if(null != o.getBillCost()) {
				n.setBillCost(o.getBillCost());
			}
			/** 填单人 */
			if(null != o.getWriteUser()) {
				n.setWriteUserId(o.getWriteUser().getId());
				n.setWriteUserName(o.getWriteUser().getUsername());
			}
			/** 审核人 */
			if(null != o.getCheckUser()) {
				n.setCheckUserId(o.getCheckUser().getId());
				n.setCheckUserName(o.getCheckUser().getUsername());
			}
			/** 填写时间 */
			if(null != o.getWriteDate()) {
				n.setWriteDate(DateFormatUtils.format(o.getWriteDate(), "yyyy-MM-dd HH:mm:ss"));
			}
			/** 审核时间 */
			if(null != o.getCheckDate()) {
				n.setCheckDate(DateFormatUtils.format(o.getCheckDate(), "yyyy-MM-dd HH:mm:ss"));
			}
			/** 单据状态 */
			if(null != o.getStatus()) {
				n.setStatus(o.getStatus());
				n.setStatusString(o.getStatus().getName());
			}
			/**
			 * 能否编辑 只有本人可以编辑
			 * */
			if("0".equals(WebUtil.getCurrentUser().getId()) || (BillStatus.WRITING.equals(o.getStatus()) && o.getWriteUser().getId().toString().equals(WebUtil.getCurrentUser().getId().toString()))) {
				n.setEditAble("true");
			} else {
				n.setEditAble("false");
			}

			/** 明细 */
			if(null != o.getDetails() && o.getDetails().size() > 0) {
				List<OutStockItemExtGridRow> itemRows = new ArrayList<OutStockItemExtGridRow>();
				for (OutStockDetail detail : o.getDetails()) {
					OutStockItemExtGridRow row = new OutStockItemExtGridRow();
					/** id */
					row.setId(detail.getId());
					/** 时间 */
					row.setCreateTime(DateFormatUtils.format(detail.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
					/** 修改时间 */
					if(null != detail.getUpdateTime()) {
						row.setUpdateTime(DateFormatUtils.format(detail.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
					}
					/** 采购申请单 */
					if(null != detail.getOutStock()) {
						row.setOutStockId(detail.getOutStock().getId());
						row.setOutStockSerialNumber(detail.getOutStock().getSerialNumber());
					}
					/** 物料 */
					if(null != detail.getMaterial()) {
						row.setMaterialId(detail.getMaterial().getId());
						row.setMaterialName(detail.getMaterial().getName());
					}
					/** 经手人 */
					if(null != o.getKeeper()) {
						n.setKeeperId(o.getKeeper().getId());
						n.setKeeperName(o.getKeeper().getName());
					}
					// 批次号
					row.setBatchNumber(detail.getBatchNumber());
					/** 仓库 */
					if(null != detail.getWarehouse()) {
						row.setWarehouseId(detail.getWarehouse().getId());
						row.setWarehouseName(detail.getWarehouse().getName());
					}
					/** 单位 */
					if(null != detail.getUnit()) {
						row.setUnitId(detail.getUnit().getId());
						row.setUnitName(detail.getUnit().getName());
					}
					/** 数量 */
					row.setBillCount(detail.getBillCount());
					/** 金额 */
					row.setBillCost(detail.getBillCost());
					/** 价格 */
					if(null != detail.getPrice()) {
						row.setPrice(detail.getPrice());
					}
					/** 备注 */
					if(null != detail.getRemark()) {
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
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/OutStock/jsonSave.html")
	public String save(OutStockExtGridRow row, ModelMap model) throws Exception {
		OutStock outStock = new OutStock();
		if(null != row.getId() && row.getId() > 0) {
			outStock = outStockService.find(row.getId());
		}
		// 保存单号
		if(null != row.getSerialNumber()) {
			outStock.setSerialNumber(row.getSerialNumber());
		}
		// 保存收发类型
		if(null != row.getInOutTypeId()) {
			outStock.setInOutType(inOutTypeService.find(row.getInOutTypeId()));
		}
		/** 经手人 */
		if(null != row.getKeeperId()) {
			outStock.setKeeper(employeeService.find(row.getKeeperId()));
		}
		/** 出库时间 */
		if(null != row.getOutDate()) {
			outStock.setOutDate(DateUtils.parseDate(row.getOutDate(), new String[] { "yyyy-MM-dd" }));
		}
		/**
		 * 保存领取人，领取部门
		 * */
		if(null != row.getReceiveDepartmentId()) {
			outStock.setReceiveDepartment(departmentService.find(row.getReceiveDepartmentId()));
		}
		if(null != row.getReceiveEmployeeId()) {
			outStock.setReceiveEmployee(employeeService.find(row.getReceiveEmployeeId()));
		}
		// 保存备注
		if(null != row.getRemark()) {
			outStock.setRemark(row.getRemark());
		}

		if(null != row.getId() && row.getId() > 0) {
			outStockService.update(outStock);
		} else {
			outStockService.saveOutStock(outStock);
		}
		// 保存物料批次信息
		if(null != row.getDetails() && row.getDetails().length() > 0) {
			List<OutStockDetail> pods = new ArrayList<OutStockDetail>();
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonArray = JSONArray.fromObject(row.getDetails());
			for (int i = 0; i < jsonArray.size(); i++) {
				OutStockDetail detail = new OutStockDetail();
				jsonObject = jsonArray.getJSONObject(i);
				String idString = jsonObject.getString("id");
				if(null != idString && idString.length() > 0) {
					detail = outStockDetailService.find(new Long(idString));
				}
				// 物料
				System.out.println("save material id:" + jsonObject.getLong("materialId"));
				detail.setMaterial(materialService.find(jsonObject.getLong("materialId")));
				// 批次号
				detail.setBatchNumber(jsonObject.getString("batchNumber"));
				// 仓库
				detail.setWarehouse(warehouseService.find(jsonObject.getLong("warehouseId")));
				detail.setPrice(new BigDecimal(jsonObject.getString("price")));

				// 单位为物料单位
				detail.setUnit(materialService.find(jsonObject.getLong("materialId")).getUnit());
				// 数量
				detail.setBillCount(new BigDecimal(jsonObject.getString("billCount")));
				// 备注
				if(null != jsonObject.getString("remark")) {
					detail.setRemark(jsonObject.getString("remark"));
				}
				detail.setOutStock(outStock);

				if(null != idString && idString.length() > 0) {
					outStockDetailService.update(detail);
				} else {
					outStockDetailService.save(detail);
				}
				pods.add(detail);
			}
			outStock.setDetails(pods);
		}
		outStockService.update(outStock);
		long id = outStock.getId() > 0 ? outStock.getId() : outStockService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/OutStock/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		outStockService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/OutStockDetail/jsonDelete.html")
	public String deleteDetail(ModelMap model, Long[] ids) {
		outStockDetailService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}

	/**
	 * 提交审核
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/warehouse/OutStock/jsonPostForCheck.html")
	public String postForCheck(ModelMap model, Long id) throws Exception {
		OutStock outStock = outStockService.find(id);
		outStock.setStatus(BillStatus.WAITING_FOR_CHECK);
		outStockService.save(outStock);
		model.addAttribute("success", true);
		return "jsonView";
	}

	/**
	 * 返回编制状态
	 */
	@RequestMapping("/warehouse/OutStock/jsonReturnForEdit.html")
	public String returnForEdit(ModelMap model, Long id) throws Exception {
		OutStock outStock = outStockService.find(id);
		if(BillStatus.CHECKED == outStock.getStatus()) {
			model.addAttribute("failure", true);
			model.addAttribute("msg", "单据已经审核过，不能再修改。");
		} else {
			outStock.setStatus(BillStatus.WRITING);
			outStockService.save(outStock);
			model.addAttribute("success", true);
		}
		return "jsonView";
	}

	/**
	 * 审核单据
	 */
	@RequestMapping("/warehouse/OutStock/jsonCheckBill.html")
	public String checkBill(ModelMap model, Long id) throws Exception {
		OutStock outStock = outStockService.find(id);
		String result = outStockService.checkOutStock(outStock);
		if(result.equals("success")) {
			model.addAttribute("success", true);
		} else {
			model.addAttribute("failure", true);
			model.addAttribute("msg", result);
		}
		return "jsonView";
	}

}
