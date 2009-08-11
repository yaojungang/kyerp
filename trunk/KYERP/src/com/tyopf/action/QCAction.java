package com.tyopf.action;

import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IAFService;
import com.tyopf.vo.AfBase;
import com.tyopf.vo.AfQualityProblem;
import com.tyopf.vo.User;

@SuppressWarnings("serial")
public class QCAction extends ActionSupport {
	protected IAFService afService;
	protected int afId;
	protected AfQualityProblem afqp;
	

	public AfQualityProblem getAfqp() {
		return afqp;
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
	public String QualityProblem_save() throws Exception {
		AfBase af = (AfBase) afService.getAFById(afId);
		afqp.setAfBase(af);
		afqp.setInputTime(new Date());
		Map session = ActionContext.getContext().getSession();
		User u = (User) session.get("user");
		afqp.setInputMan(u.getEmployee().getRealname());
		afService.saveAfQualityProblem(afqp);
		AfQualityProblem afqpGet = afService.getLastAfQualityProblem();
		System.out.println("afqpGet:"+afqpGet.getId());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("AFInfo", af);
		request.put("AFQualityProblem", afqpGet);
		request.put("pageTitle", "【"+af.getPresswork()+"】质量问题");
		request.put("message", "【"+af.getPresswork()+"】质量问题保存成功！");
		return SUCCESS;
	}

}
