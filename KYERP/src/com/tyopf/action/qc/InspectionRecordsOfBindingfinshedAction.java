package com.tyopf.action.qc;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IAFService;
import com.tyopf.service.qc.IInspectionRecordsOfBindingfinshedService;
import com.tyopf.util.Pager;
import com.tyopf.vo.qc.InspectionRecordsOfBindingfinshed;

@SuppressWarnings("serial")
public class InspectionRecordsOfBindingfinshedAction extends ActionSupport
		implements ServletContextAware, SessionAware {
	private IInspectionRecordsOfBindingfinshedService	irs;
	private IAFService									iafService;
	private int											afId;
	private final Integer								currentPage	= 1;
	private final Integer								pageSize	= 50;
	private int											id;
	@SuppressWarnings("unchecked")
	private Map											session;
	@SuppressWarnings("unchecked")
	private Map											request;
	private InspectionRecordsOfBindingfinshed			ir;

	public IInspectionRecordsOfBindingfinshedService getIrs() {
		return irs;
	}

	public void setIrs(IInspectionRecordsOfBindingfinshedService irs) {
		this.irs = irs;
	}

	public IAFService getIafService() {
		return iafService;
	}

	public void setIafService(IAFService iafService) {
		this.iafService = iafService;
	}

	public InspectionRecordsOfBindingfinshed getIr() {
		return ir;
	}

	public void setIr(InspectionRecordsOfBindingfinshed ir) {
		this.ir = ir;
	}

	public IInspectionRecordsOfBindingfinshedService getInspectionRecordsOfBindingfinshedService() {
		return irs;
	}

	public void setInspectionRecordsOfBindingfinshedService(
			IInspectionRecordsOfBindingfinshedService inspectionRecordsOfBindingfinshedService) {
		this.irs = inspectionRecordsOfBindingfinshedService;
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

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public Map getRequest() {
		return request;
	}

	public void setRequest(Map request) {
		this.request = request;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	@Override
	public void setServletContext(ServletContext arg0) {

	}

	public String add() throws Exception {
		return SUCCESS;
	}

	public String save() throws Exception {
		irs.save(ir);
		return SUCCESS;
	}

	public String find() throws Exception {
		irs.find(id);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		InspectionRecordsOfBindingfinshed ins = new InspectionRecordsOfBindingfinshed();
		ins.setAfBase(iafService.getAFById(9000));
		ins.setExamItem01("fdsafdsf");
		irs.save(ins);

		Pager pager = new Pager(currentPage, irs.getCountAll());
		pager.setPageSize(pageSize);
		List list = irs.getList(currentPage, pageSize);
		request.put("list", list);
		request.put("Pager", pager);
		return SUCCESS;
	}

	public String remove() throws Exception {
		irs.remove(id);
		return SUCCESS;
	}

}
