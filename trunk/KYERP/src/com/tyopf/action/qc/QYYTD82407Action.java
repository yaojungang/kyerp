package com.tyopf.action.qc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IAFService;
import com.tyopf.service.qc.IQYYTD82407Service;
import com.tyopf.util.Pager;
import com.tyopf.vo.AfBase;
import com.tyopf.vo.Employee;
import com.tyopf.vo.qc.QYYTD82407;

@SuppressWarnings("serial")
public class QYYTD82407Action extends ActionSupport {
	private IQYYTD82407Service	irs;
	private IAFService								afService;
	private int										afId;
	private int										currentPage	= 1;
	private int										pageSize	= 50;
	private int										id;
	private QYYTD82407			ir;

	public IQYYTD82407Service getIrs() {
		return irs;
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

	public void setIrs(IQYYTD82407Service irs) {
		this.irs = irs;
	}

	public IAFService getAfService() {
		return afService;
	}

	public void setAfService(IAFService afService) {
		this.afService = afService;
	}

	public QYYTD82407 getIr() {
		return ir;
	}

	public void setIr(QYYTD82407 ir) {
		this.ir = ir;
	}

	public IQYYTD82407Service getQYYTD82407Service() {
		return irs;
	}

	public void setQYYTD82407Service(
			IQYYTD82407Service QYYTD82407Service) {
		this.irs = QYYTD82407Service;
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
		QYYTD82407 ir = new QYYTD82407();

		ir.setExamItem01("合格");
		ir.setExamItem02("合格");
		ir.setExamItem03("合格");
		ir.setExamItem04("合格");
		ir.setExamItem05("合格");
		ir.setExamItem06("合格");
		ir.setExamItem07("合格");
		ir.setExamItem08("合格");
		ir.setExamItem09("合格");
		ir.setExamItem10("合格");
		ir.setExamResult("合格");

		request.put("afBase", afBase);
		request.put("ir", ir);
		return SUCCESS;
	}

	public String save() throws Exception {
		AfBase afBase = afService.getAFById(afId);
		afBase.setQYYTD82407(ir);
		ir.setAfBase(afBase);
		Map session = ActionContext.getContext().getSession();
		Employee employee = (Employee) session.get("employee");
		ir.setExamEmployee(employee);
		irs.save(ir);
		// afService.saveAF(afBase);
		return SUCCESS;
	}

	public String find() throws Exception {
		irs.find(id);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String edit() throws Exception {
		AfBase afBase = afService.getAFById(afId);
		QYYTD82407 ir = irs.find(id);
		Map request = (Map) ActionContext.getContext().get("request");
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
