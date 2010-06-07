package org.kyerp.web.controller.crm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.crm.Client;
import org.kyerp.service.crm.IClientService;
import org.kyerp.service.crm.IClientTypeService;
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
public class ClientController extends BaseController{
	@Autowired
	IClientService		clientService;
	@Autowired
	IClientTypeService	clientTypeService;

	@RequestMapping("/crm/Client/jsonList.html")
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
			wherejpql.append(" and o.clientType.id=?").append(queryParams.size() + 1);
			queryParams.add(typeId);
		}
		// set query
		if(null != query && !query.equals("")) {
			wherejpql.append(" and (o.fullName like ?").append(queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// name
			wherejpql.append(" or o.name like ?").append(queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// nameSpell
			wherejpql.append(" or o.nameSpell like ?").append(queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// remark
			wherejpql.append(" or o.remark like ?").append(queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// address
			wherejpql.append(" or o.address like ?").append(queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// phone
			wherejpql.append(" or o.phone like ?").append(queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");

			wherejpql.append(" or o.serialNumber like ?").append(queryParams.size() + 1).append(")");
			queryParams.add("%" + query.trim() + "%");
		}
		logger.info(wherejpql.toString() + "  -  " + queryParams.toArray());
		QueryResult<Client> queryResult = clientService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);

		List<ClientExtGridRow> rows = new ArrayList<ClientExtGridRow>();
		for (Client o : queryResult.getResultlist()) {
			ClientExtGridRow n = new ClientExtGridRow();
			n.setId(o.getId());
			/** 分类 */
			if(null != o.getClientType()) {
				n.setTypeId(o.getClientType().getId());
				n.setTypeName(o.getClientType().getName());

			}
			/** 供应商简称 **/
			n.setName(o.getName());
			/** 供应商简拼 **/
			n.setNameSpell(o.getNameSpell());
			/** 供应商全称 **/
			n.setFullName(o.getFullName());
			/** 是否可见 **/
			n.setVisible(o.getVisible());
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
			/** 应收款 */
			n.setAccountReceivable(o.getAccountReceivable());
			/** logo图片路径 如:/images/brand/2008/12/12/ooo.gif" **/
			n.setLogopath(o.getLogopath());
			rows.add(n);
		}
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/crm/Client/jsonSave.html")
	public String save(ClientExtGridRow o, ModelMap model) {
		Client n = new Client();
		if(null != o.getId() && o.getId() > 0) {
			n = clientService.find(o.getId());
		}
		/** 分类 */
		if(o.getTypeId() > 0) {
			n.setClientType(clientTypeService.find(o.getTypeId()));

		}
		/** 供应商简称 **/
		n.setName(o.getName());
		/** 供应商简拼 **/
		n.setNameSpell(o.getNameSpell());
		/** 供应商全称 **/
		n.setFullName(o.getFullName());
		/** 是否可见 **/
		n.setVisible(o.getVisible());
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
		/** 应收款 */
		n.setAccountReceivable(o.getAccountReceivable());
		/** logo图片路径 如:/images/brand/2008/12/12/ooo.gif" **/
		n.setLogopath(o.getLogopath());
		if(null != o.getId() && o.getId() > 0) {
			clientService.update(n);
		} else {
			clientService.save(n);
		}
		long id = n.getId() > 0 ? n.getId() : clientService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/crm/Client/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		clientService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}
}
