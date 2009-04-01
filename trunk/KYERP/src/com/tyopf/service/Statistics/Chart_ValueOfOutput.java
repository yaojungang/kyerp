package com.tyopf.service.Statistics;

import java.util.Iterator;

import com.tyopf.service.IAFService;
import com.tyopf.vo.AfBase;
import com.tyopf.vo.AfValuation;

public class Chart_ValueOfOutput {
	protected IAFService afService;
	private ValueOfOutput valueOfOutput;
	
	public IAFService getAfService() {
		return afService;
	}

	public void setAfService(IAFService afService) {
		this.afService = afService;
	}

	public ValueOfOutput getValueOfOutput() {
		return valueOfOutput;
	}

	public void setValueOfOutput(ValueOfOutput valueOfOutput) {
		this.valueOfOutput = valueOfOutput;
	}

	public ValueOfOutput getChart_ValueOfOutput(long SKStartAFNo,
			long LHStartAFNo, long SKEndAFNo, long LHEndAFNo) {
		valueOfOutput.setSKStartAFNo(SKStartAFNo);
		valueOfOutput.setLHStartAFNo(LHStartAFNo);
		valueOfOutput.setSKEndAFNo(SKEndAFNo);
		valueOfOutput.setLHEndAFNo(LHEndAFNo);
		
		// 按业务类型条件
		for (Iterator<AfBase> it = afService.getAFByNoRange("SK", valueOfOutput.getSKStartAFNo(), valueOfOutput.getSKEndAFNo()).iterator(); it.hasNext();) {
			AfBase af = it.next();
			if (null != af.getMoneyShould())
				valueOfOutput.setSKMoneyShould(valueOfOutput.getSKMoneyShould() + af.getMoneyShould());
			if(null != af.getMoneyFact())
				valueOfOutput.setSKMoneyFact(valueOfOutput.getSKMoneyFact() + af.getMoneyFact());
		}
		
		for (Iterator<AfBase> it = afService.getAFByNoRange("LH", LHStartAFNo, LHEndAFNo).iterator(); it.hasNext();) {
			AfBase af = it.next();
			if (null != af.getMoneyShould())
				valueOfOutput.setLHMoneyShould(valueOfOutput.getLHMoneyShould() + af.getMoneyShould());
			if(null != af.getMoneyFact())
				valueOfOutput.setLHMoneyShould(valueOfOutput.getLHMoneyShould() + af.getMoneyShould());
		}
		
		//各车间统计
		for (Iterator<AfValuation> it = afService.getAFVByChejian("", "", "SK", valueOfOutput.getSKStartAFNo(), valueOfOutput.getSKEndAFNo()).iterator(); it.hasNext();) {
			AfValuation afv = it.next();
			if("照排".equals(afv.getChejian())) valueOfOutput.setSKzp(valueOfOutput.getSKzp() + afv.getTotalAmount());
			if("制版车间".equals(afv.getChejian())) valueOfOutput.setSKzbcj(valueOfOutput.getSKzbcj() + afv.getTotalAmount());
			if("四色".equals(afv.getChejian())) valueOfOutput.setSKss(valueOfOutput.getSKss() + afv.getTotalAmount());
			if("双面".equals(afv.getChejian())) valueOfOutput.setSKsm(valueOfOutput.getSKsm() + afv.getTotalAmount());
			if("05".equals(afv.getChejian())) valueOfOutput.setSK05(valueOfOutput.getSK05() + afv.getTotalAmount());
			if("02".equals(afv.getChejian())) valueOfOutput.setSK02(valueOfOutput.getSK02() + afv.getTotalAmount());
			if("小制版".equals(afv.getChejian())) valueOfOutput.setSKxzb(valueOfOutput.getSKxzb() + afv.getTotalAmount());
			if("装订".equals(afv.getChejian())) valueOfOutput.setSKzd(valueOfOutput.getSKzd() + afv.getTotalAmount());
			if("库房".equals(afv.getChejian())) valueOfOutput.setSKkf(valueOfOutput.getSKkf() + afv.getTotalAmount());
			if("外加工".equals(afv.getChejian())) valueOfOutput.setSKwjg(valueOfOutput.getSKwjg() + afv.getTotalAmount());
			if("其它".equals(afv.getChejian())) valueOfOutput.setSKqt(valueOfOutput.getSKqt() + afv.getTotalAmount());
		}
		
		for (Iterator<AfValuation> it = afService.getAFVByChejian("", "", "LH", LHStartAFNo, LHEndAFNo).iterator(); it.hasNext();) {
			AfValuation afv = it.next();
			if("照排".equals(afv.getChejian())) valueOfOutput.setLHzp(valueOfOutput.getLHzp() + afv.getTotalAmount());
			if("制版车间".equals(afv.getChejian())) valueOfOutput.setLHzbcj(valueOfOutput.getLHzbcj() + afv.getTotalAmount());
			if("四色".equals(afv.getChejian())) valueOfOutput.setLHss(valueOfOutput.getLHss() + afv.getTotalAmount());
			if("双面".equals(afv.getChejian())) valueOfOutput.setLHsm(valueOfOutput.getLHsm() + afv.getTotalAmount());
			if("05".equals(afv.getChejian())) valueOfOutput.setLH05(valueOfOutput.getLH05() + afv.getTotalAmount());
			if("02".equals(afv.getChejian())) valueOfOutput.setLH02(valueOfOutput.getLH02() + afv.getTotalAmount());
			if("小制版".equals(afv.getChejian())) valueOfOutput.setLHxzb(valueOfOutput.getLHxzb() + afv.getTotalAmount());
			if("装订".equals(afv.getChejian())) valueOfOutput.setLHzd(valueOfOutput.getLHzd() + afv.getTotalAmount());
			if("库房".equals(afv.getChejian())) valueOfOutput.setLHkf(valueOfOutput.getLHkf() + afv.getTotalAmount());
			if("外加工".equals(afv.getChejian())) valueOfOutput.setLHwjg(valueOfOutput.getLHwjg() + afv.getTotalAmount());
			if("其它".equals(afv.getChejian())) valueOfOutput.setLHqt(valueOfOutput.getLHqt() + afv.getTotalAmount());
		}
		
		//按业务员统计
		for (Iterator<AfBase> it = afService.getAFByNoRange("SK", valueOfOutput.getSKStartAFNo(), valueOfOutput.getSKEndAFNo()).iterator(); it.hasNext();) {
			AfBase af = it.next();
			if("王秀云".equals(af.getCp())) {
				if (null != af.getMoneyShould()) valueOfOutput.setSKYWYShouldwxy(valueOfOutput.getSKYWYShouldwxy() + af.getMoneyShould());
				if(null != af.getMoneyFact()) valueOfOutput.setSKYWYFactwxy(valueOfOutput.getSKYWYFactwxy() + af.getMoneyFact());
			}
			if("杨爱荣".equals(af.getCp())) {
				if (null != af.getMoneyShould()) valueOfOutput.setSKYWYShouldyar(valueOfOutput.getSKYWYShouldyar() + af.getMoneyShould());
				if(null != af.getMoneyFact()) valueOfOutput.setSKYWYFactyar(valueOfOutput.getSKYWYFactyar() + af.getMoneyFact());
			}
			if("陈桂芝".equals(af.getCp())) {
				if (null != af.getMoneyShould()) valueOfOutput.setSKYWYShouldcgz(valueOfOutput.getSKYWYShouldcgz() + af.getMoneyShould());
				if(null != af.getMoneyFact()) valueOfOutput.setSKYWYFactcgz(valueOfOutput.getSKYWYFactcgz() + af.getMoneyFact());
			}
			if("孙玉萍".equals(af.getCp())) {
				if (null != af.getMoneyShould()) valueOfOutput.setSKYWYShouldsyp(valueOfOutput.getSKYWYShouldsyp() + af.getMoneyShould());
				if(null != af.getMoneyFact()) valueOfOutput.setSKYWYFactsyp(valueOfOutput.getSKYWYFactsyp() + af.getMoneyFact());
			}
			if("孙纪文".equals(af.getCp())) {
				if (null != af.getMoneyShould()) valueOfOutput.setSKYWYShouldsjw(valueOfOutput.getSKYWYShouldsjw() + af.getMoneyShould());
				if(null != af.getMoneyFact()) valueOfOutput.setSKYWYFactsjw(valueOfOutput.getSKYWYFactsjw() + af.getMoneyFact());
			}
			if("吴宝举".equals(af.getCp())) {
				if (null != af.getMoneyShould()) valueOfOutput.setSKYWYShouldwbj(valueOfOutput.getSKYWYShouldwbj() + af.getMoneyShould());
				if(null != af.getMoneyFact()) valueOfOutput.setSKYWYFactwbj(valueOfOutput.getSKYWYFactwbj() + af.getMoneyFact());
			}
		}
		for (Iterator<AfBase> it = afService.getAFByNoRange("LH", LHStartAFNo, LHEndAFNo).iterator(); it.hasNext();) {
			AfBase af = it.next();
			if("王秀云".equals(af.getCp())) {
				if (null != af.getMoneyShould()) valueOfOutput.setLHYWYShouldwxy(valueOfOutput.getLHYWYShouldwxy() + af.getMoneyShould());
				if(null != af.getMoneyFact()) valueOfOutput.setLHYWYFactwxy(valueOfOutput.getLHYWYFactwxy() + af.getMoneyFact());
			}
			if("杨爱荣".equals(af.getCp())) {
				if (null != af.getMoneyShould()) valueOfOutput.setLHYWYShouldyar(valueOfOutput.getLHYWYShouldyar() + af.getMoneyShould());
				if(null != af.getMoneyFact()) valueOfOutput.setLHYWYFactyar(valueOfOutput.getLHYWYFactyar() + af.getMoneyFact());
			}
			if("陈桂芝".equals(af.getCp())) {
				if (null != af.getMoneyShould()) valueOfOutput.setLHYWYShouldcgz(valueOfOutput.getLHYWYShouldcgz() + af.getMoneyShould());
				if(null != af.getMoneyFact()) valueOfOutput.setLHYWYFactcgz(valueOfOutput.getLHYWYFactcgz() + af.getMoneyFact());
			}
			if("孙玉萍".equals(af.getCp())) {
				if (null != af.getMoneyShould()) valueOfOutput.setLHYWYShouldsyp(valueOfOutput.getLHYWYShouldsyp() + af.getMoneyShould());
				if(null != af.getMoneyFact()) valueOfOutput.setLHYWYFactsyp(valueOfOutput.getLHYWYFactsyp() + af.getMoneyFact());
			}
			if("孙纪文".equals(af.getCp())) {
				if (null != af.getMoneyShould()) valueOfOutput.setLHYWYShouldsjw(valueOfOutput.getLHYWYShouldsjw() + af.getMoneyShould());
				if(null != af.getMoneyFact()) valueOfOutput.setLHYWYFactsjw(valueOfOutput.getLHYWYFactsjw() + af.getMoneyFact());
			}
			if("吴宝举".equals(af.getCp())) {
				if (null != af.getMoneyShould()) valueOfOutput.setLHYWYShouldwbj(valueOfOutput.getLHYWYShouldwbj() + af.getMoneyShould());
				if(null != af.getMoneyFact()) valueOfOutput.setLHYWYFactwbj(valueOfOutput.getLHYWYFactwbj() + af.getMoneyFact());
			}
		}
		
		

		return valueOfOutput;
	}

}
