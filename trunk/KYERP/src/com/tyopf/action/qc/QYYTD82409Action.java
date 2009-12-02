package com.tyopf.action.qc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IAFService;
import com.tyopf.service.ISystemService;
import com.tyopf.service.qc.IInspectionRecordsOfPatrolAndExamineForSingletonPressworkService;
import com.tyopf.util.Pager;
import com.tyopf.vo.AfBase;
import com.tyopf.vo.Employee;
import com.tyopf.vo.qc.InspectionRecordsOfPatrolAndExamineForSingletonPresswork;

@SuppressWarnings("serial")
public class QYYTD82409Action extends ActionSupport {
	private IInspectionRecordsOfPatrolAndExamineForSingletonPressworkService	irs;
	private IAFService															afService;
	private ISystemService														systemService;
	private int																	afId;
	private int																	currentPage	= 1;
	private int																	pageSize	= 50;
	private int																	id;
	private InspectionRecordsOfPatrolAndExamineForSingletonPresswork			ir;

	public IInspectionRecordsOfPatrolAndExamineForSingletonPressworkService getIrs() {
		return irs;
	}

	public ISystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(ISystemService systemService) {
		this.systemService = systemService;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setIrs(
			IInspectionRecordsOfPatrolAndExamineForSingletonPressworkService irs) {
		this.irs = irs;
	}

	public IAFService getAfService() {
		return afService;
	}

	public void setAfService(IAFService afService) {
		this.afService = afService;
	}

	public InspectionRecordsOfPatrolAndExamineForSingletonPresswork getIr() {
		return ir;
	}

	public void setIr(
			InspectionRecordsOfPatrolAndExamineForSingletonPresswork ir) {
		this.ir = ir;
	}

	public IInspectionRecordsOfPatrolAndExamineForSingletonPressworkService getInspectionRecordsOfPatrolAndExamineForSingletonPressworkService() {
		return irs;
	}

	public void setInspectionRecordsOfPatrolAndExamineForSingletonPressworkService(
			IInspectionRecordsOfPatrolAndExamineForSingletonPressworkService InspectionRecordsOfPatrolAndExamineForSingletonPressworkService) {
		this.irs = InspectionRecordsOfPatrolAndExamineForSingletonPressworkService;
	}

	public int getAfId() {
		return afId;
	}

	public void setAfId(int afId) {
		this.afId = afId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@SuppressWarnings("unchecked")
	public String add() throws Exception {
		AfBase afBase = afService.getAFById(afId);
		Map request = (Map) ActionContext.getContext().get("request");
		Map session = ActionContext.getContext().getSession();
		InspectionRecordsOfPatrolAndExamineForSingletonPresswork ir = new InspectionRecordsOfPatrolAndExamineForSingletonPresswork();
		ir.setExamDate(new Date());
		ir.setExamItem01("合格");
		ir.setExamItem02("合格");
		ir.setExamItem03("合格");
		ir.setExamItem04("合格");
		ir.setExamItem05("合格");
		ir.setExamResult("合格");
		ir.setFinishDate(afBase.getComDeliver());
		List deptTree = (List) session.get("DeptTree");
		if (null == deptTree) {
			deptTree = systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		request.put("afBase", afBase);
		request.put("ir", ir);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String save() throws Exception {
		AfBase afBase = afService.getAFById(afId);
		afBase.setInspectionRecordsOfPatrolAndExamineForSingletonPresswork(ir);
		ir.setAfBase(afBase);
		Map session = ActionContext.getContext().getSession();
		Employee employee = (Employee) session.get("employee");
		ir.setExamEmployee(employee);
		irs.save(ir);
		return SUCCESS;
	}

	public String find() throws Exception {
		irs.find(id);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String edit() throws Exception {
		AfBase afBase = afService.getAFById(afId);
		InspectionRecordsOfPatrolAndExamineForSingletonPresswork ir = irs
				.find(id);
		Map request = (Map) ActionContext.getContext().get("request");
		Map session = ActionContext.getContext().getSession();
		List deptTree = (List) session.get("DeptTree");
		if (null == deptTree) {
			deptTree = systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		request.put("ir", ir);
		request.put("afBase", afBase);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String list() throws Exception {

		Pager pager = new Pager(currentPage, irs.getCountAll());
		pager.setPageSize(pageSize);
		List list = new ArrayList();
		list = irs.getList(currentPage, pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("list", list);
		request.put("pager", pager);
		return SUCCESS;
	}

	public String remove() throws Exception {
		irs.remove(id);
		return SUCCESS;
	}

}
