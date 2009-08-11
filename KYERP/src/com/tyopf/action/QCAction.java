package com.tyopf.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IAFService;
import com.tyopf.util.Pager;
import com.tyopf.vo.AfBase;
import com.tyopf.vo.AfQualityProblem;
import com.tyopf.vo.User;

@SuppressWarnings("serial")
public class QCAction extends ActionSupport {
	protected IAFService afService;
	protected int afId;
	protected AfQualityProblem afqp;
	private Integer currentPage = 1;
	private Integer pageSize = 50;
	private int id;

	public AfQualityProblem getAfqp() {
		return afqp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setAfqp(AfQualityProblem afqp) {
		this.afqp = afqp;
	}

	public int getAfId() {
		return afId;
	}

	public void setAfId(int afId) {
		this.afId = afId;
	}

	public IAFService getAfService() {
		return afService;
	}

	public void setAfService(IAFService afService) {
		this.afService = afService;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String index() throws Exception {
		return SUCCESS;
	}
	public String getQualityProblemList() throws Exception {
		List listqp = afService.getAllQualityProblem(currentPage, pageSize);
		Pager Pager = new Pager(currentPage, afService.getCountofAllQualityProblem());
		Pager.setPageSize(pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("QualityProblemList", listqp);
		request.put("Pager", Pager);
		request.put("pageTitle", "质量问题记录表");
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String QualityProblemInput() throws Exception {
		AfBase af = (AfBase) afService.getAFById(afId);
		if (af != null) {
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("AFInfo", af);
			request.put("pageTitle", "【"+af.getPresswork()+"】质量问题录入");
			return SUCCESS;
		}
		return ERROR;
	}
	@SuppressWarnings("unchecked")
	public String QualityProblemInfo() throws Exception {
		AfBase af = (AfBase) afService.getAFById(afId);
		AfQualityProblem afg = afService.getAFQPById(id);
		if (af != null) {
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("AFInfo", af);
			request.put("AFQualityProblem", afg);
			request.put("pageTitle", "【"+af.getPresswork()+"】质量问题");
			return SUCCESS;
		}
		return ERROR;
	}
	@SuppressWarnings("unchecked")
	public String QualityProblem_save() throws Exception {
		AfBase af = (AfBase) afService.getAFById(afId);
		afqp.setAfBase(af);
		afqp.setInputTime(new Date());
		Map session = ActionContext.getContext().getSession();
		User u = (User) session.get("user");
		afqp.setInputMan(u.getEmployee().getRealname());
		afService.saveAfQualityProblem(afqp);
		//AfQualityProblem afqpGet = new AfQualityProblem();
		if(afqp.getAfId()==0) afqp = afService.getLastAfQualityProblem();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("AFInfo", af);
		request.put("AFQualityProblem", afqp);
		request.put("pageTitle", "【"+af.getPresswork()+"】质量问题00");
		request.put("message", "【"+af.getPresswork()+"】质量问题保存成功！");
		return SUCCESS;
	}

}
