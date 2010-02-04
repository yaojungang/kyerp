package com.tyopf.action;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IAFService;
import com.tyopf.service.ISystemService;
import com.tyopf.util.Pager;
import com.tyopf.vo.AfBase;
import com.tyopf.vo.AfQualityProblem;
import com.tyopf.vo.AfQualityProblemAttachment;
import com.tyopf.vo.User;

@SuppressWarnings("serial")
public class QCAction extends ActionSupport implements ServletContextAware,
		SessionAware {
	protected IAFService		afService;
	protected ISystemService	systemService;
	protected int				afId;
	protected AfQualityProblem	afqp;
	private Integer				currentPage	= 1;
	private Integer				pageSize	= 50;
	private int					id;
	private Map					session;
	private Map					request;
	private ServletContext		context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public ISystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(ISystemService systemService) {
		this.systemService = systemService;
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
		Pager pager = new Pager(currentPage, afService.getCountofAllAFs());
		pager.setPageSize(pageSize);
		List<AfBase> list = afService.getAFs(currentPage, pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("list", list);
		request.put("pager", pager);
		return SUCCESS;
	}

	public String getQualityProblemList() throws Exception {
		List listqp = afService.getAllQualityProblem(currentPage, pageSize);
		Pager Pager = new Pager(currentPage, afService
				.getCountofAllQualityProblem());
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
		List deptTree = (List) session.get("DeptTree");
		if (null == deptTree) {
			deptTree = systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		if (af != null) {
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("AFInfo", af);
			request.put("DeptTree", deptTree);
			request.put("pageTitle", "【" + af.getPresswork() + "】质量问题录入");
			return SUCCESS;
		}
		return ERROR;
	}

	@SuppressWarnings("unchecked")
	public String QualityProblemInfo() throws Exception {
		AfBase af = (AfBase) afService.getAFById(afId);
		AfQualityProblem afg = afService.getAFQPById(id);
		List deptTree = (List) session.get("DeptTree");
		if (null == deptTree) {
			deptTree = systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		if (af != null) {
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("AFInfo", af);
			request.put("DeptTree", deptTree);
			request.put("AFQualityProblem", afg);
			request.put("pageTitle", "【" + af.getPresswork() + "】质量问题");
			return SUCCESS;
		}
		return ERROR;
	}

	@SuppressWarnings("unchecked")
	public String QualityProblem_save() throws Exception {
		AfBase af = (AfBase) afService.getAFById(afId);
		List deptTree = (List) session.get("DeptTree");
		if (null == deptTree) {
			deptTree = systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		afqp.setAfBase(af);
		afqp.setInputTime(new Date());
		Map session = ActionContext.getContext().getSession();
		User u = (User) session.get("user");
		afqp.setInputMan(u.getEmployee().getRealname());
		afService.saveAfQualityProblem(afqp);
		// AfQualityProblem afqpGet = new AfQualityProblem();
		if ("".equals(afqp.getId()) || 0 == afqp.getId()) {
			afqp = afService.getLastAfQualityProblem();
		} else {
			afqp = afService.getAFQPById(afqp.getId());
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("AFInfo", af);
		request.put("AFQualityProblem", afqp);
		request.put("pageTitle", "【" + af.getPresswork() + "】质量问题");
		request.put("DeptTree", deptTree);
		request.put("message", "【" + af.getPresswork() + "】质量问题保存成功！");
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String delQualityProblemById() throws Exception {
		String uploadPath = systemService.getSystemVarByName("dataPath");
		String targetDirectory = context.getRealPath(uploadPath
				+ "/QualityProblemAttactent/");
		AfQualityProblem afqp = afService.getAFQPById(id);
		if (null != afqp.getAttachments()) {
			for (Iterator iterator = afqp.getAttachments().iterator(); iterator
					.hasNext();) {
				AfQualityProblemAttachment a = (AfQualityProblemAttachment) iterator
						.next();
				File file = new File(targetDirectory, a.getFileName());
				FileUtils.deleteQuietly(file);
			}
		}
		afService.removeAfQualityProblem(id);
		AfBase af = (AfBase) afService.getAFById(afId);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("AFInfo", af);
		request.put("message", "删除成功！");
		return SUCCESS;
	}
}
