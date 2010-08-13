package org.kyerp.web.controller.warehouse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.InStockDetail;
import org.kyerp.domain.warehouse.InventoryDetail;
import org.kyerp.domain.warehouse.OutStockDetail;
import org.kyerp.service.warehouse.IInventoryDetailService;
import org.kyerp.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class InventoryDetailController extends BaseController {
	@Autowired
	IInventoryDetailService inventoryDetailService;

	@RequestMapping("/warehouse/InventoryDetail/jsonList.html")
	public String list(Model model, Integer start, Integer limit,
			Long materialId, String query, String batchNumber,
			String startDate, String endDate,Long ownerId) throws Exception {
		Date startDate1 = null;
		Date endDate1 = null;
		if (null != startDate && startDate.length() > 0) {
			startDate1 = DateUtils.parseDate(startDate + " 00:00:00",
					new String[] { "yyyy-MM-dd HH:mm:ss" });
		}
		if (null != endDate && endDate.length() > 0) {
			endDate1 = DateUtils.parseDate(endDate + " 23:59:29",
					new String[] { "yyyy-MM-dd HH:mm:ss" });
		}
		QueryResult<InventoryDetail> queryResult = getList(model, start, limit,
				materialId, query, batchNumber, startDate1, endDate1,ownerId);

		List<InventoryDetailExtGridRow> rows = new ArrayList<InventoryDetailExtGridRow>();
		for (InventoryDetail o : queryResult.getResultlist()) {
			InventoryDetailExtGridRow n = new InventoryDetailExtGridRow();
			n.setId(o.getId());
			/** 建立时间 */
			n.setCreateTime(DateFormatUtils.format(o.getCreateTime(),
					"yyyy-MM-dd HH:mm:ss"));
			/** 修改时间 */
			if (null != o.getUpdateTime()) {
				n.setUpdateTime(DateFormatUtils.format(o.getUpdateTime(),
						"yyyy-MM-dd HH:mm:ss"));
			}
			/** 物料 */
			if (null != o.getMaterial()) {
				n.setMaterialId(o.getMaterial().getId());
				n.setMaterialName(o.getMaterial().getName());
			}
			/** 批次号 */
			n.setBatchNumber(o.getBatchNumber());
			/** 单据号 */
			if (o instanceof InStockDetail) {
				InStockDetail inStockDetail = (InStockDetail) o;
				n.setSerialNumber(inStockDetail.getInStock().getSerialNumber());
				// 收发类型
				n.setInOutType(inStockDetail.getInStock().getInOutType()
						.getName());
			}
			if (o instanceof OutStockDetail) {
				OutStockDetail outStockDetail = (OutStockDetail) o;
				n.setSerialNumber(outStockDetail.getOutStock()
						.getSerialNumber());
				// 生产任务单号
				n.setPressworkNo(outStockDetail.getPressworkNo());
				// 收发类型
				n.setInOutType(outStockDetail.getOutStock().getInOutType()
						.getName());
			}
			/** 期初余额 */
			n.setBegingStockCount(o.getBegingStockCount());
			/** 入库数量 */
			n.setInStockCount(o.getInStockCount());
			/** 出库数量 */
			n.setOutStockCount(o.getOutStockCount());
			/** 当前余额 **/
			n.setCurrentStockCount(o.getCurrentStockCount());
			/** 仓库 */
			if (null != o.getWarehouse()) {
				n.setWarehouseId(o.getWarehouse().getId());
				n.setWarehouseName(o.getWarehouse().getName());
			}

			/** 单位 */
			if (null != o.getUnit()) {
				n.setUnitId(o.getUnit().getId());
				n.setUnitName(o.getUnit().getName());
			}
			/** 价格 */
			n.setPrice(o.getPrice());
			/** 总金额 */
			n.setCost(o.getCost());
			//所有者
			if (null != o.getOwner()) {
				n.setOwnerId(o.getOwner().getId());
				n.setOwnerName(o.getOwner().getName());
			}
			rows.add(n);
		}
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	private QueryResult<InventoryDetail> getList(Model model, Integer start,
			Integer limit, Long materialId, String query, String batchNumber,
			Date startDate, Date endDate,Long ownerId) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		// build where jpql
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" 1=?").append((queryParams.size() + 1));
		queryParams.add(1);
		// set owner id
		if (null != ownerId) {
			wherejpql.append(" and o.owner.id=?").append(
					queryParams.size() + 1);
			queryParams.add(ownerId);
		}
		// set parent id
		if (null != materialId) {
			wherejpql.append(" and o.material.id=?").append(
					queryParams.size() + 1);
			queryParams.add(materialId);
		}
		// set batchNumber
		if (null != batchNumber && !batchNumber.equals("")
				&& batchNumber.trim().length() > 0) {
			wherejpql.append(" and o.batchNumber like?").append(
					queryParams.size() + 1);
			queryParams.add("%" + batchNumber.trim() + "%");
		}
		// set startDate
		if (null != startDate) {
			wherejpql.append(" and o.happenDate >=?").append(
					queryParams.size() + 1);
			queryParams.add(startDate);
		}
		// set endDate
		if (null != endDate) {
			wherejpql.append(" and o.happenDate <=?").append(
					queryParams.size() + 1);
			queryParams.add(endDate);
		}
		// set query
		if (null != query && !query.equals("") && query.trim().length() > 0) {
			wherejpql.append(" and (o.material.name like ?").append(
					queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// material's serialNumber
			wherejpql.append(" or o.material.serialNumber like ?")
					.append(queryParams.size() + 1).append(")");
			queryParams.add("%" + query.trim() + "%");
		}
		//logger.debug("查询出入库明细\njpql:" + wherejpql + "\n "+ queryParams.toString());
		QueryResult<InventoryDetail> queryResult = inventoryDetailService
				.getScrollData(start, limit, wherejpql.toString(),
						queryParams.toArray(), orderby);

		return queryResult;
	}

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(df,
				true));
	}
}
