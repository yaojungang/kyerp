package com.tyopf.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IAFService;
import com.tyopf.service.Statistics.ValueOfOutput;
import com.tyopf.vo.AfBase;
import com.tyopf.vo.AfElement;
import com.tyopf.vo.AfValuation;

@SuppressWarnings("serial")
public class MAAction extends ActionSupport {
	protected IAFService afService;

	private long SKStartAFNo = 20081001;
	private long LHStartAFNo = 20080671;
	private long SKEndAFNo = 20081300;
	private long LHEndAFNo = 20080800;
	private String AFType = "SK";
	private long StartAFNo = 20070001;
	private long EndAFNo = 20079999;

	public String getAFType() {
		return AFType;
	}

	public void setAFType(String type) {
		AFType = type;
	}

	public long getStartAFNo() {
		return StartAFNo;
	}

	public void setStartAFNo(long startAFNo) {
		StartAFNo = startAFNo;
	}

	public long getEndAFNo() {
		return EndAFNo;
	}

	public void setEndAFNo(long endAFNo) {
		EndAFNo = endAFNo;
	}

	public long getLHEndAFNo() {
		return LHEndAFNo;
	}

	public void setLHEndAFNo(long endAFNo) {
		LHEndAFNo = endAFNo;
	}

	public long getLHStartAFNo() {
		return LHStartAFNo;
	}

	public void setLHStartAFNo(long startAFNo) {
		LHStartAFNo = startAFNo;
	}

	public long getSKEndAFNo() {
		return SKEndAFNo;
	}

	public void setSKEndAFNo(long endAFNo) {
		SKEndAFNo = endAFNo;
	}

	public long getSKStartAFNo() {
		return SKStartAFNo;
	}

	public void setSKStartAFNo(long startAFNo) {
		SKStartAFNo = startAFNo;
	}

	public IAFService getAfService() {
		return afService;
	}

	public void setAfService(IAFService afService) {
		this.afService = afService;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String index() throws Exception {
		List ListAF = afService.getTodayAFs();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		return SUCCESS;
	}

	public String cztjtb_input() throws Exception {
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String cztjtb() throws Exception {

		Map request = (Map) ActionContext.getContext().get("request");
		request.put("SKStartAFNo", SKStartAFNo);
		request.put("LHStartAFNo", LHStartAFNo);
		request.put("SKEndAFNo", SKEndAFNo);
		request.put("LHEndAFNo", LHEndAFNo);

		// 按业务类型条件
		double SKMoneyShould = 0.0;
		double LHMoneyShould = 0.0;
		double SKMoneyFact = 0.0;
		double LHMoneyFact = 0.0;

		for (Iterator it = afService.getAFByNoRange("SK", SKStartAFNo,
				SKEndAFNo).iterator(); it.hasNext();) {
			AfBase af = (AfBase) it.next();
			if (null != af.getMoneyShould())
				SKMoneyShould = SKMoneyShould + af.getMoneyShould();
			if (null != af.getMoneyFact())
				SKMoneyFact = SKMoneyFact + af.getMoneyFact();
		}

		for (Iterator it = afService.getAFByNoRange("LH", LHStartAFNo,
				LHEndAFNo).iterator(); it.hasNext();) {
			AfBase af = (AfBase) it.next();
			if (null != af.getMoneyShould())
				LHMoneyShould = LHMoneyShould + af.getMoneyShould();
			if (null != af.getMoneyFact())
				LHMoneyFact = LHMoneyFact + af.getMoneyFact();
		}
		request.put("SKMoneyShould", com.tyopf.util.MathTools.round(
				SKMoneyShould, 2));
		request.put("LHMoneyShould", com.tyopf.util.MathTools.round(
				LHMoneyShould, 2));
		request.put("SKMoneyFact", com.tyopf.util.MathTools.round(SKMoneyFact,
				2));
		request.put("LHMoneyFact", com.tyopf.util.MathTools.round(LHMoneyFact,
				2));

		// 各车间统计
		double SKzp = 0.0;
		double SKzbcj = 0.0;
		double SKss = 0.0;
		double SKsm = 0.0;
		double SK05 = 0.0;
		double SK02 = 0.0;
		double SKxzb = 0.0;
		double SKzd = 0.0;
		double SKkf = 0.0;
		double SKwjg = 0.0;
		double SKqt = 0.0;

		double LHzp = 0.0;
		double LHzbcj = 0.0;
		double LHss = 0.0;
		double LHsm = 0.0;
		double LH05 = 0.0;
		double LH02 = 0.0;
		double LHxzb = 0.0;
		double LHzd = 0.0;
		double LHkf = 0.0;
		double LHwjg = 0.0;
		double LHqt = 0.0;

		for (Iterator it = afService.getAFVByChejian("", "", "SK", SKStartAFNo,
				SKEndAFNo).iterator(); it.hasNext();) {
			AfValuation afv = (AfValuation) it.next();
			if ("照排".equals(afv.getChejian()))
				SKzp = SKzp + afv.getTotalAmount();
			if ("制版车间".equals(afv.getChejian()))
				SKzbcj = SKzbcj + afv.getTotalAmount();
			if ("四色".equals(afv.getChejian()))
				SKss = SKss + afv.getTotalAmount();
			if ("双面".equals(afv.getChejian()))
				SKsm = SKsm + afv.getTotalAmount();
			if ("05".equals(afv.getChejian()))
				SK05 = SK05 + afv.getTotalAmount();
			if ("02".equals(afv.getChejian()))
				SK02 = SK02 + afv.getTotalAmount();
			if ("小制版".equals(afv.getChejian()))
				SKxzb = SKxzb + afv.getTotalAmount();
			if ("装订".equals(afv.getChejian()))
				SKzd = SKzd + afv.getTotalAmount();
			if ("库房".equals(afv.getChejian()))
				SKkf = SKkf + afv.getTotalAmount();
			if ("外加工".equals(afv.getChejian()))
				SKwjg = SKwjg + afv.getTotalAmount();
			if ("其它".equals(afv.getChejian()))
				SKqt = SKqt + afv.getTotalAmount();
		}

		for (Iterator it = afService.getAFVByChejian("", "", "LH", LHStartAFNo,
				LHEndAFNo).iterator(); it.hasNext();) {
			AfValuation afv = (AfValuation) it.next();
			if ("照排".equals(afv.getChejian()))
				LHzp = LHzp + afv.getTotalAmount();
			if ("制版车间".equals(afv.getChejian()))
				LHzbcj = LHzbcj + afv.getTotalAmount();
			if ("四色".equals(afv.getChejian()))
				LHss = LHss + afv.getTotalAmount();
			if ("双面".equals(afv.getChejian()))
				LHsm = LHsm + afv.getTotalAmount();
			if ("05".equals(afv.getChejian()))
				LH05 = LH05 + afv.getTotalAmount();
			if ("02".equals(afv.getChejian()))
				LH02 = LH02 + afv.getTotalAmount();
			if ("小制版".equals(afv.getChejian()))
				LHxzb = LHxzb + afv.getTotalAmount();
			if ("装订".equals(afv.getChejian()))
				LHzd = LHzd + afv.getTotalAmount();
			if ("库房".equals(afv.getChejian()))
				LHkf = LHkf + afv.getTotalAmount();
			if ("外加工".equals(afv.getChejian()))
				LHwjg = LHwjg + afv.getTotalAmount();
			if ("其它".equals(afv.getChejian()))
				LHqt = LHqt + afv.getTotalAmount();
		}

		request.put("SKzp", com.tyopf.util.MathTools.round(SKzp, 2));
		request.put("SKzbcj", com.tyopf.util.MathTools.round(SKzbcj, 2));
		request.put("SKss", com.tyopf.util.MathTools.round(SKss, 2));
		request.put("SKsm", com.tyopf.util.MathTools.round(SKsm, 2));
		request.put("SK05", com.tyopf.util.MathTools.round(SK05, 2));
		request.put("SK02", com.tyopf.util.MathTools.round(SK02, 2));
		request.put("SKxzb", com.tyopf.util.MathTools.round(SKxzb, 2));
		request.put("SKzd", com.tyopf.util.MathTools.round(SKzd, 2));
		request.put("SKkf", com.tyopf.util.MathTools.round(SKkf, 2));
		request.put("SKwjg", com.tyopf.util.MathTools.round(SKwjg, 2));
		request.put("SKqt", com.tyopf.util.MathTools.round(SKqt, 2));

		request.put("LHzp", com.tyopf.util.MathTools.round(LHzp, 2));
		request.put("LHzbcj", com.tyopf.util.MathTools.round(LHzbcj, 2));
		request.put("LHss", com.tyopf.util.MathTools.round(LHss, 2));
		request.put("LHsm", com.tyopf.util.MathTools.round(LHsm, 2));
		request.put("LH05", com.tyopf.util.MathTools.round(LH05, 2));
		request.put("LH02", com.tyopf.util.MathTools.round(LH02, 2));
		request.put("LHxzb", com.tyopf.util.MathTools.round(LHxzb, 2));
		request.put("LHzd", com.tyopf.util.MathTools.round(LHzd, 2));
		request.put("LHkf", com.tyopf.util.MathTools.round(LHkf, 2));
		request.put("LHwjg", com.tyopf.util.MathTools.round(LHwjg, 2));
		request.put("LHqt", com.tyopf.util.MathTools.round(LHqt, 2));

		// 按业务员统计
		double SKYWYShouldwxy = 0.0;
		double SKYWYFactwxy = 0.0;
		double SKYWYShouldyar = 0.0;
		double SKYWYFactyar = 0.0;
		double SKYWYShouldcgz = 0.0;
		double SKYWYFactcgz = 0.0;
		double SKYWYShouldsyp = 0.0;
		double SKYWYFactsyp = 0.0;
		double SKYWYShouldsjw = 0.0;
		double SKYWYFactsjw = 0.0;
		double SKYWYShouldwbj = 0.0;
		double SKYWYFactwbj = 0.0;

		double LHYWYShouldwxy = 0.0;
		double LHYWYFactwxy = 0.0;
		double LHYWYShouldyar = 0.0;
		double LHYWYFactyar = 0.0;
		double LHYWYShouldcgz = 0.0;
		double LHYWYFactcgz = 0.0;
		double LHYWYShouldsyp = 0.0;
		double LHYWYFactsyp = 0.0;
		double LHYWYShouldsjw = 0.0;
		double LHYWYFactsjw = 0.0;
		double LHYWYShouldwbj = 0.0;
		double LHYWYFactwbj = 0.0;

		for (Iterator it = afService.getAFByNoRange("SK", SKStartAFNo,
				SKEndAFNo).iterator(); it.hasNext();) {
			AfBase af = (AfBase) it.next();
			if ("王秀云".equals(af.getCp())) {
				if (null != af.getMoneyShould())
					SKYWYShouldwxy = SKYWYShouldwxy + af.getMoneyShould();
				if (null != af.getMoneyFact())
					SKYWYFactwxy = SKYWYFactwxy + af.getMoneyFact();
			}
			if ("杨爱荣".equals(af.getCp())) {
				if (null != af.getMoneyShould())
					SKYWYShouldyar = SKYWYShouldyar + af.getMoneyShould();
				if (null != af.getMoneyFact())
					SKYWYFactyar = SKYWYFactyar + af.getMoneyFact();
			}
			if ("陈桂芝".equals(af.getCp())) {
				if (null != af.getMoneyShould())
					SKYWYShouldcgz = SKYWYShouldcgz + af.getMoneyShould();
				if (null != af.getMoneyFact())
					SKYWYFactcgz = SKYWYFactcgz + af.getMoneyFact();
			}
			if ("孙玉萍".equals(af.getCp())) {
				if (null != af.getMoneyShould())
					SKYWYShouldsyp = SKYWYShouldsyp + af.getMoneyShould();
				if (null != af.getMoneyFact())
					SKYWYFactsyp = SKYWYFactsyp + af.getMoneyFact();
			}
			if ("孙纪文".equals(af.getCp())) {
				if (null != af.getMoneyShould())
					SKYWYShouldsjw = SKYWYShouldsjw + af.getMoneyShould();
				if (null != af.getMoneyFact())
					SKYWYFactsjw = SKYWYFactsjw + af.getMoneyFact();
			}
			if ("吴宝举".equals(af.getCp())) {
				if (null != af.getMoneyShould())
					SKYWYShouldwbj = SKYWYShouldwbj + af.getMoneyShould();
				if (null != af.getMoneyFact())
					SKYWYFactwbj = SKYWYFactwbj + af.getMoneyFact();
			}
		}

		for (Iterator it = afService.getAFByNoRange("LH", LHStartAFNo,
				LHEndAFNo).iterator(); it.hasNext();) {
			AfBase af = (AfBase) it.next();
			if ("王秀云".equals(af.getCp())) {
				if (null != af.getMoneyShould())
					LHYWYShouldwxy = LHYWYShouldwxy + af.getMoneyShould();
				if (null != af.getMoneyFact())
					LHYWYFactwxy = LHYWYFactwxy + af.getMoneyFact();
			}
			if ("杨爱荣".equals(af.getCp())) {
				if (null != af.getMoneyShould())
					LHYWYShouldyar = LHYWYShouldyar + af.getMoneyShould();
				if (null != af.getMoneyFact())
					LHYWYFactyar = LHYWYFactyar + af.getMoneyFact();
			}
			if ("陈桂芝".equals(af.getCp())) {
				if (null != af.getMoneyShould())
					LHYWYShouldcgz = LHYWYShouldcgz + af.getMoneyShould();
				if (null != af.getMoneyFact())
					LHYWYFactcgz = LHYWYFactcgz + af.getMoneyFact();
			}
			if ("孙玉萍".equals(af.getCp())) {
				if (null != af.getMoneyShould())
					LHYWYShouldsyp = LHYWYShouldsyp + af.getMoneyShould();
				if (null != af.getMoneyFact())
					LHYWYFactsyp = LHYWYFactsyp + af.getMoneyFact();
			}
			if ("孙纪文".equals(af.getCp())) {
				if (null != af.getMoneyShould())
					LHYWYShouldsjw = LHYWYShouldsjw + af.getMoneyShould();
				if (null != af.getMoneyFact())
					LHYWYFactsjw = LHYWYFactsjw + af.getMoneyFact();
			}
			if ("吴宝举".equals(af.getCp())) {
				if (null != af.getMoneyShould())
					LHYWYShouldwbj = LHYWYShouldwbj + af.getMoneyShould();
				if (null != af.getMoneyFact())
					LHYWYFactwbj = LHYWYFactwbj + af.getMoneyFact();
			}
		}

		request.put("SKYWYShouldwxy", com.tyopf.util.MathTools.round(
				SKYWYShouldwxy, 2));
		request.put("SKYWYFactwxy", com.tyopf.util.MathTools.round(
				SKYWYFactwxy, 2));
		request.put("SKYWYShouldyar", com.tyopf.util.MathTools.round(
				SKYWYShouldyar, 2));
		request.put("SKYWYFactyar", com.tyopf.util.MathTools.round(
				SKYWYFactyar, 2));
		request.put("SKYWYShouldcgz", com.tyopf.util.MathTools.round(
				SKYWYShouldcgz, 2));
		request.put("SKYWYFactcgz", com.tyopf.util.MathTools.round(
				SKYWYFactcgz, 2));
		request.put("SKYWYShouldsyp", com.tyopf.util.MathTools.round(
				SKYWYShouldsyp, 2));
		request.put("SKYWYFactsyp", com.tyopf.util.MathTools.round(
				SKYWYFactsyp, 2));
		request.put("SKYWYShouldsjw", com.tyopf.util.MathTools.round(
				SKYWYShouldsjw, 2));
		request.put("SKYWYFactsjw", com.tyopf.util.MathTools.round(
				SKYWYFactsjw, 2));
		request.put("SKYWYShouldwbj", com.tyopf.util.MathTools.round(
				SKYWYShouldwbj, 2));
		request.put("SKYWYFactwbj", com.tyopf.util.MathTools.round(
				SKYWYFactwbj, 2));

		request.put("LHYWYShouldwxy", com.tyopf.util.MathTools.round(
				LHYWYShouldwxy, 2));
		request.put("LHYWYFactwxy", com.tyopf.util.MathTools.round(
				LHYWYFactwxy, 2));
		request.put("LHYWYShouldyar", com.tyopf.util.MathTools.round(
				LHYWYShouldyar, 2));
		request.put("LHYWYFactyar", com.tyopf.util.MathTools.round(
				LHYWYFactyar, 2));
		request.put("LHYWYShouldcgz", com.tyopf.util.MathTools.round(
				LHYWYShouldcgz, 2));
		request.put("LHYWYFactcgz", com.tyopf.util.MathTools.round(
				LHYWYFactcgz, 2));
		request.put("LHYWYShouldsyp", com.tyopf.util.MathTools.round(
				LHYWYShouldsyp, 2));
		request.put("LHYWYFactsyp", com.tyopf.util.MathTools.round(
				LHYWYFactsyp, 2));
		request.put("LHYWYShouldsjw", com.tyopf.util.MathTools.round(
				LHYWYShouldsjw, 2));
		request.put("LHYWYFactsjw", com.tyopf.util.MathTools.round(
				LHYWYFactsjw, 2));
		request.put("LHYWYShouldwbj", com.tyopf.util.MathTools.round(
				LHYWYShouldwbj, 2));
		request.put("LHYWYFactwbj", com.tyopf.util.MathTools.round(
				LHYWYFactwbj, 2));

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getValueOfOutputOfThisMonth() throws Exception{
//		SKStartAFNo = 20081001;
//		LHStartAFNo = 20080671;
//		SKEndAFNo = 20081300;
//		LHEndAFNo = 20080800;
		ValueOfOutput ValueOfOutput = afService.getChart_ValueOfOutput(20081001, 20080671, 20081300, 20080800);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ValueOfOutput", ValueOfOutput);		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String taizhang_input() throws Exception {
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String taizhang() throws Exception {
		List ListAF = afService.getAFByNoRange(AFType, StartAFNo, EndAFNo);
		Map request = (Map) ActionContext.getContext().get("request");
		//Pager AFpager = new Pager(currentPage, ListAF.size());
		//AFpager.setPageSize(ListAF.size());
		//request.put("AFPager", AFpager);
		request.put("ListAF", ListAF);
		//System.out.println(ListAF.size());
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String TjInput() throws Exception {
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String Tj() throws Exception {
		List<AfBase> ListAF = afService.getAFByNoRange(AFType, StartAFNo, EndAFNo);
		long printAmount = 0;
		double epsAmount = 0.0;
		double paperAmount = 0.0;
		for(AfBase af : ListAF){
			printAmount = printAmount + af.getAmount();
			for(AfElement afe : af.getAfElement()){
				epsAmount = epsAmount + afe.getEPs();
				paperAmount = paperAmount + afe.getEReam()+afe.getEOvers();
			}
			
		}
		
		
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("ListAF", ListAF);
		request.put("printAmount", printAmount);
		request.put("epsAmount",com.tyopf.util.MathTools.round(epsAmount,2));
		request.put("paperAmount",com.tyopf.util.MathTools.round(paperAmount,2));
		return SUCCESS;
	}

}
