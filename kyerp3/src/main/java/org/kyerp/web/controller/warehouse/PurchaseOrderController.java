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
import org.kyerp.domain.warehouse.PurchaseOrder;
import org.kyerp.domain.warehouse.PurchaseOrderDetail;
import org.kyerp.service.org.IDepartmentService;
import org.kyerp.service.org.IEmployeeService;
import org.kyerp.service.security.IUserService;
import org.kyerp.service.warehouse.IInOutTypeService;
import org.kyerp.service.warehouse.IMaterialService;
import org.kyerp.service.warehouse.IPurchaseOrderDetailService;
import org.kyerp.service.warehouse.IPurchaseOrderService;
import org.kyerp.service.warehouse.ISupplierService;
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
public class PurchaseOrderController extends BaseController{
	@Autowired
	IPurchaseOrderService		purchaseOrderService;
	@Autowired
	IPurchaseOrderDetailService	purchaseOrderDetailService;
	@Autowired
	IMaterialService			materialService;
	@Autowired
	ISupplierService			supplierService;
	@Autowired
	IUserService				userService;
	@Autowired
	IDepartmentService			departmentService;
	@Autowired
	IEmployeeService			employeeService;
	@Autowired
	IInOutTypeService			inOutTypeService;

	@RequestMapping("/warehouse/PurchaseOrder/jsonList.html")
	public String list(Model model, Integer start, Integer limit) throws Exception {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		QueryResult<PurchaseOrder> queryResult = purchaseOrderService.getScrollData(start, limit, orderby);
		List<PurchaseOrderExtGridRow> rows = new ArrayList<PurchaseOrderExtGridRow>();
		for (PurchaseOrder o : queryResult.getResultlist()) {
			PurchaseOrderExtGridRow n = new PurchaseOrderExtGridRow();
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
			/** 申请类型 */
			if(null != o.getInOutType()) {
				n.setInOutTypeId(o.getInOutType().getId());
				n.setInOutTypeName(o.getInOutType().getName());
			}

			/** 供应商 */
			if(null != o.getSupplier()) {
				n.setSupplierId(o.getSupplier().getId());
				n.setSupplierName(o.getSupplier().getName());
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
			/** 申请部门 */
			if(null != o.getApplicationDepartment()) {
				n.setApplicationDepartmentId(o.getApplicationDepartment().getId());
				n.setApplicationDepartmentName(o.getApplicationDepartment().getName());
			}
			/** 申请人 */
			if(null != o.getApplicant()) {
				n.setApplicantId(o.getApplicant().getId());
				n.setApplicantName(o.getApplicant().getName());
			}
			/** 填单人 */
			if(null != o.getWriteUser()) {
				n.setWriteUserId(o.getWriteUser().getId());
				n.setWriteUserName(o.getWriteUser().getUsername());
			}
			if(null != o.getWriteEmployee()) {
				n.setWriteEmployeeId(o.getWriteEmployee().getId());
				n.setWriteEmployeeName(o.getWriteEmployee().getName());
			}
			/** 审核人 */
			if(null != o.getCheckUser()) {
				n.setCheckUserId(o.getCheckUser().getId());
				n.setCheckUserName(o.getCheckUser().getUsername());
			}
			if(null != o.getCheckEmployee()) {
				n.setCheckEmployeeId(o.getCheckEmployee().getId());
				n.setCheckEmployeeName(o.getCheckEmployee().getName());
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

			/** 到货日期 */
			if(null != o.getArriveDate()) {
				n.setArriveDate(DateFormatUtils.format(o.getArriveDate(), "yyyy-MM-dd"));
			}
			/** 明细 */
			if(null != o.getDetails() && o.getDetails().size() > 0) {
				List<PurchaseOrderItemExtGridRow> itemRows = new ArrayList<PurchaseOrderItemExtGridRow>();
				for (PurchaseOrderDetail detail : o.getDetails()) {
					PurchaseOrderItemExtGridRow row = new PurchaseOrderItemExtGridRow();
					/** id */
					row.setId(detail.getId());
					/** 时间 */
					row.setCreateTime(DateFormatUtils.format(detail.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
					/** 修改时间 */
					if(null != detail.getUpdateTime()) {
						row.setUpdateTime(DateFormatUtils.format(detail.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
					}
					/** 采购申请单 */
					if(null != detail.getPurchaseOrder()) {
						row.setPurchaseOrderId(detail.getPurchaseOrder().getId());
						row.setPurchaseOrderSerialNumber(detail.getPurchaseOrder().getSerialNumber());
					}
					/** 物料 */
					if(null != detail.getMaterial()) {
						row.setMaterialId(detail.getMaterial().getId());
						row.setMaterialName(detail.getMaterial().getName());
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
					/** 收货数量 */
					if(null != detail.getRecvCount()) {
						row.setRecvCount(detail.getRecvCount());
					}

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
	@RequestMapping("/warehouse/PurchaseOrder/jsonSave.html")
	public String save(PurchaseOrderExtGridRow row, ModelMap model) throws Exception {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		if(null != row.getId() && row.getId() > 0) {
			purchaseOrder = purchaseOrderService.find(row.getId());
		}
		// 保存单号
		if(null != row.getSerialNumber()) {
			purchaseOrder.setSerialNumber(row.getSerialNumber());
		}
		// 保存到货时间
		if(null != row.getArriveDate()) {
			purchaseOrder.setArriveDate(DateUtils.parseDate(row.getArriveDate(), new String[] { "yyyy-MM-dd" }));
		}
		// 保存填单人
		// purchaseOrder.setWriteUser(WebUtil.getCurrentUser());
		// purchaseOrder.setWriteEmployee(WebUtil.getCurrentEmployee());
		/** 申请类型 */
		if(null != row.getInOutTypeId()) {
			purchaseOrder.setInOutType(inOutTypeService.find(row.getInOutTypeId()));
		}
		/** 申请部门 */
		if(null != row.getApplicationDepartmentId()) {
			purchaseOrder.setApplicationDepartment(departmentService.find(row.getApplicationDepartmentId()));
		}
		/** 申请人 */
		if(null != row.getApplicantId()) {
			purchaseOrder.setApplicant(employeeService.find(row.getApplicantId()));
		}
		/** 供应商 */
		if(null != row.getSupplierId()) {
			purchaseOrder.setSupplier(supplierService.find(row.getSupplierId()));
		}
		// 保存备注
		if(null != row.getRemark()) {
			purchaseOrder.setRemark(row.getRemark());
		}

		if(null != row.getId() && row.getId() > 0) {
			purchaseOrderService.update(purchaseOrder);
		} else {
			purchaseOrderService.savePurchaseOrder(purchaseOrder);
		}
		// 保存物料批次信息
		if(null != row.getDetails() && row.getDetails().length() > 0) {
			List<PurchaseOrderDetail> pods = new ArrayList<PurchaseOrderDetail>();
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonArray = JSONArray.fromObject(row.getDetails());
			for (int i = 0; i < jsonArray.size(); i++) {
				PurchaseOrderDetail detail = new PurchaseOrderDetail();
				jsonObject = jsonArray.getJSONObject(i);
				String idString = jsonObject.getString("id");
				if(null != idString && idString.length() > 0) {
					detail = purchaseOrderDetailService.find(new Long(idString));
				}
				detail.setMaterial(materialService.find(jsonObject.getLong("materialId")));
				detail.setPrice(new BigDecimal(jsonObject.getString("price")));

				// 单位为物料单位
				detail.setUnit(materialService.find(jsonObject.getLong("materialId")).getUnit());
				// 数量
				detail.setBillCount(new BigDecimal(jsonObject.getString("billCount")));
				// 备注
				if(null != jsonObject.getString("remark")) {
					detail.setRemark(jsonObject.getString("remark"));
				}
				detail.setPurchaseOrder(purchaseOrder);

				if(null != idString && idString.length() > 0) {
					purchaseOrderDetailService.update(detail);
				} else {
					purchaseOrderDetailService.save(detail);
				}
				pods.add(detail);
			}
			purchaseOrder.setDetails(pods);
		}
		purchaseOrderService.update(purchaseOrder);
		long id = purchaseOrder.getId() > 0 ? purchaseOrder.getId() : purchaseOrderService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/PurchaseOrder/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		purchaseOrderService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/PurchaseOrderDetail/jsonDelete.html")
	public String deleteDetail(ModelMap model, Long[] ids) {
		purchaseOrderDetailService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}

	/**
	 * 提交审核
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/warehouse/PurchaseOrder/jsonPostForCheck.html")
	public String postForCheck(ModelMap model, Long id) throws Exception {
		PurchaseOrder purchaseOrder = purchaseOrderService.find(id);
		purchaseOrder.setStatus(BillStatus.WAITING_FOR_CHECK);
		purchaseOrderService.save(purchaseOrder);
		model.addAttribute("success", true);
		return "jsonView";
	}

	/**
	 * 返回编制状态
	 */
	@RequestMapping("/warehouse/PurchaseOrder/jsonReturnForEdit.html")
	public String returnForEdit(ModelMap model, Long id) throws Exception {
		PurchaseOrder purchaseOrder = purchaseOrderService.find(id);
		if(BillStatus.CHECKED == purchaseOrder.getStatus()) {
			model.addAttribute("failure", true);
			model.addAttribute("msg", "单据已经审核过，不能再修改。");
		} else {
			purchaseOrder.setStatus(BillStatus.WRITING);
			purchaseOrderService.save(purchaseOrder);
			model.addAttribute("success", true);
		}
		return "jsonView";
	}

	/**
	 * 审核单据
	 */
	@RequestMapping("/warehouse/PurchaseOrder/jsonCheckBill.html")
	public String checkBill(ModelMap model, Long id) throws Exception {
		PurchaseOrder purchaseOrder = purchaseOrderService.find(id);
		String result = purchaseOrderService.checkPurchaseOrder(purchaseOrder);
		if(result.equals("success")) {
			model.addAttribute("success", true);
		} else {
			model.addAttribute("failure", true);
			model.addAttribute("msg", result);
		}
		return "jsonView";
	}

}
