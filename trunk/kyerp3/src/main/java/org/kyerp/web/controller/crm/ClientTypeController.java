package org.kyerp.web.controller.crm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.common.view.ExtTreeNode;
import org.kyerp.domain.common.view.ExtTreeRecursion;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.crm.ClientType;
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
public class ClientTypeController extends BaseController{
	@Autowired
	IClientTypeService	clientTypeService;

	@RequestMapping("/crm/ClientType/jsonList.html")
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
			wherejpql.append(" and parentClientType.id=?").append(queryParams.size() + 1);
			queryParams.add(parentId);
		}
		QueryResult<ClientType> queryResult = clientTypeService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);
		List<ClientTypeExtGridRow> rows = new ArrayList<ClientTypeExtGridRow>();
		for (ClientType o : queryResult.getResultlist()) {
			ClientTypeExtGridRow n = new ClientTypeExtGridRow();
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
			if(null != o.getParentClientType()) {
				n.setParentClientTypeId(o.getParentClientType().getId());
				n.setParentClientTypeName(o.getParentClientType().getName());
			} else {
				n.setParentClientTypeId(0);
				n.setParentClientTypeName("顶级分类");
			}
			rows.add(n);
		}
		;
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@RequestMapping("/crm/ClientType/jsonTree.html")
	public String tree(Model model) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<ClientType> queryResult = clientTypeService.getScrollData(orderby);
		List<ExtTreeNode> extTreeList = new ArrayList<ExtTreeNode>();
		if(queryResult.getResultlist().size() == 0) {
			ClientType clientType = new ClientType();
			clientType.setName("客户分类");
			clientTypeService.save(clientType);
			model.addAttribute("jsonText", "[{id:1,text:'客户分类',leaf:true}]");
		} else {
			for (ClientType d : queryResult.getResultlist()) {
				ExtTreeNode node = new ExtTreeNode();
				node.setId(String.valueOf(d.getId()));
				node.setText(d.getName());
				if(null != d.getParentClientType() && d.getParentClientType().getId() > 0) {
					node.setParentId(String.valueOf(d.getParentClientType().getId()));
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
	@RequestMapping("/crm/ClientType/jsonSave.html")
	public String save(ClientTypeExtGridRow clientTypeRow, ModelMap model) {
		ClientType clientType = new ClientType();
		if(null != clientTypeRow.getId() && clientTypeRow.getId() > 0) {
			clientType = clientTypeService.find(clientTypeRow.getId());
		}
		clientType.setName(clientTypeRow.getName());
		// 设置父类
		if(clientTypeRow.getParentClientTypeId() != 0) {
			clientType.setParentClientType(clientTypeService.find(clientTypeRow.getParentClientTypeId()));
		}
		// 设置note
		if(null != clientTypeRow.getNote()) {
			clientType.setNote(clientTypeRow.getNote());
		}
		// 设置序号
		if(null != clientTypeRow.getSerialNumber()) {
			clientType.setSerialNumber(clientTypeRow.getSerialNumber());
		}
		if(null != clientTypeRow.getId() && clientTypeRow.getId() > 0) {
			clientTypeService.update(clientType);
		} else {
			clientTypeService.save(clientType);
		}
		long id = clientType.getId() > 0 ? clientType.getId() : clientTypeService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/crm/ClientType/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		clientTypeService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}
}
