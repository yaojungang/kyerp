package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.InventoryOwner;
import org.kyerp.service.warehouse.IInventoryOwnerService;
import org.kyerp.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
@RequestMapping("/warehouse/InventoryOwner/")
@SessionAttributes("inventoryOwner")
public class InventoryOwnerController extends BaseController{
	@Autowired
	IInventoryOwnerService inventoryOwnerService;

	@RequestMapping("/jsonList.html")
	public String list(Long parentId, Integer start, Integer limit, Model model) {
		try {
			start = null == start ? 0 : start;
			limit = null == limit ? 20 : limit;
			QueryResult<InventoryOwner> queryResult = inventoryOwnerService
					.getScrollData(start, limit);
			List<InventoryOwnerExtGridRow> rows = new ArrayList<InventoryOwnerExtGridRow>();
			for (InventoryOwner o : queryResult.getResultlist()) {
				InventoryOwnerExtGridRow n = new InventoryOwnerExtGridRow();
				n.setId(o.getId());
				n.setName(o.getName());
				n.setCreateTime(DateFormatUtils.format(o.getCreateTime(),
						"yyyy-MM-dd HH:mm:ss"));
				/** 修改时间 */
				if (null != o.getUpdateTime()) {
					n.setUpdateTime(DateFormatUtils.format(o.getUpdateTime(),
							"yyyy-MM-dd HH:mm:ss"));
				}
				n.setSerialNumber(o.getSerialNumber());
				n.setName(o.getName());
				rows.add(n);
			}
			model.addAttribute("totalProperty", queryResult.getTotalrecord());
			model.addAttribute("rows", rows);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("failure", true);
			model.addAttribute("message", "获取数据失败！<br />" + e.getLocalizedMessage());
		}
		return "jsonView";
	}	

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/jsonSave.html")
	public String save(InventoryOwnerExtGridRow inventoryOwnerRow, ModelMap model) {
		try {
			InventoryOwner inventoryOwner = null;
			if (null != inventoryOwnerRow.getId()
					&& inventoryOwnerRow.getId() > 0) {
				inventoryOwner = inventoryOwnerService.find(inventoryOwnerRow
						.getId());
			} else {
				inventoryOwner = new InventoryOwner();
			}
			inventoryOwner.setName(inventoryOwnerRow.getName());
			if (null != inventoryOwnerRow.getSerialNumber()) {
				inventoryOwner.setSerialNumber(inventoryOwnerRow
						.getSerialNumber());
			}
			if (null != inventoryOwnerRow.getId()
					&& inventoryOwnerRow.getId() > 0) {
				inventoryOwnerService.update(inventoryOwner);
			} else {
				inventoryOwnerService.save(inventoryOwner);
			}
			model.addAttribute("success", true);
			model.addAttribute("message", "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("failure", true);
			model.addAttribute("message", "保存数据失败！<br />" + e.getLocalizedMessage());
		}
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		inventoryOwnerService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}
}
