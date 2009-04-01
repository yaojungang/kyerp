package com.tyopf.service.Statistics;

public class ValueOfOutput {
	public long SKStartAFNo = 20081001;
	public long LHStartAFNo = 20080671;
	public long SKEndAFNo = 20081300;
	public long LHEndAFNo = 20080800;
	
	// 按业务类型条件
	private double SKMoneyShould = 0.0;
	private double LHMoneyShould = 0.0;
	private double SKMoneyFact = 0.0;
	private double LHMoneyFact = 0.0;
	// 各车间统计
	private double SKzp = 0.0;
	private double SKzbcj = 0.0;
	private double SKss = 0.0;
	private double SKsm = 0.0;
	private double SK05 = 0.0;
	private double SK02 = 0.0;
	private double SKxzb = 0.0;
	private double SKzd = 0.0;
	private double SKkf = 0.0;
	private double SKwjg = 0.0;
	private double SKqt = 0.0;

	private double LHzp = 0.0;
	private double LHzbcj = 0.0;
	private double LHss = 0.0;
	private double LHsm = 0.0;
	private double LH05 = 0.0;
	private double LH02 = 0.0;
	private double LHxzb = 0.0;
	private double LHzd = 0.0;
	private double LHkf = 0.0;
	private double LHwjg = 0.0;
	private double LHqt = 0.0;
	// 按业务员统计
	private double SKYWYShouldwxy = 0.0;
	private double SKYWYFactwxy = 0.0;
	private double SKYWYShouldyar = 0.0;
	private double SKYWYFactyar = 0.0;
	private double SKYWYShouldcgz = 0.0;
	private double SKYWYFactcgz = 0.0;
	private double SKYWYShouldsyp = 0.0;
	private double SKYWYFactsyp = 0.0;
	private double SKYWYShouldsjw = 0.0;
	private double SKYWYFactsjw = 0.0;
	private double SKYWYShouldwbj = 0.0;
	private double SKYWYFactwbj = 0.0;

	private double LHYWYShouldwxy = 0.0;
	private double LHYWYFactwxy = 0.0;
	private double LHYWYShouldyar = 0.0;
	private double LHYWYFactyar = 0.0;
	private double LHYWYShouldcgz = 0.0;
	private double LHYWYFactcgz = 0.0;
	private double LHYWYShouldsyp = 0.0;
	private double LHYWYFactsyp = 0.0;
	private double LHYWYShouldsjw = 0.0;
	private double LHYWYFactsjw = 0.0;
	private double LHYWYShouldwbj = 0.0;
	private double LHYWYFactwbj = 0.0;
	
	public ValueOfOutput(long SKStartAFNo,
			long LHStartAFNo, long SKEndAFNo, long LHEndAFNo){
	}
	
	public long getSKStartAFNo() {
		return SKStartAFNo;
	}

	public void setSKStartAFNo(long startAFNo) {
		SKStartAFNo = startAFNo;
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

	public long getLHEndAFNo() {
		return LHEndAFNo;
	}

	public void setLHEndAFNo(long endAFNo) {
		LHEndAFNo = endAFNo;
	}

	public double getSKMoneyShould() {
		return SKMoneyShould;
	}
	public void setSKMoneyShould(double moneyShould) {
		SKMoneyShould = moneyShould;
	}
	public double getLHMoneyShould() {
		return LHMoneyShould;
	}
	public void setLHMoneyShould(double moneyShould) {
		LHMoneyShould = moneyShould;
	}
	public double getSKMoneyFact() {
		return SKMoneyFact;
	}
	public void setSKMoneyFact(double moneyFact) {
		SKMoneyFact = moneyFact;
	}
	public double getLHMoneyFact() {
		return LHMoneyFact;
	}
	public void setLHMoneyFact(double moneyFact) {
		LHMoneyFact = moneyFact;
	}
	public double getSKzp() {
		return SKzp;
	}
	public void setSKzp(double kzp) {
		SKzp = kzp;
	}
	public double getSKzbcj() {
		return SKzbcj;
	}
	public void setSKzbcj(double kzbcj) {
		SKzbcj = kzbcj;
	}
	public double getSKss() {
		return SKss;
	}
	public void setSKss(double kss) {
		SKss = kss;
	}
	public double getSKsm() {
		return SKsm;
	}
	public void setSKsm(double ksm) {
		SKsm = ksm;
	}
	public double getSK05() {
		return SK05;
	}
	public void setSK05(double sk05) {
		SK05 = sk05;
	}
	public double getSK02() {
		return SK02;
	}
	public void setSK02(double sk02) {
		SK02 = sk02;
	}
	public double getSKxzb() {
		return SKxzb;
	}
	public void setSKxzb(double kxzb) {
		SKxzb = kxzb;
	}
	public double getSKzd() {
		return SKzd;
	}
	public void setSKzd(double kzd) {
		SKzd = kzd;
	}
	public double getSKkf() {
		return SKkf;
	}
	public void setSKkf(double kkf) {
		SKkf = kkf;
	}
	public double getSKwjg() {
		return SKwjg;
	}
	public void setSKwjg(double kwjg) {
		SKwjg = kwjg;
	}
	public double getSKqt() {
		return SKqt;
	}
	public void setSKqt(double kqt) {
		SKqt = kqt;
	}
	public double getLHzp() {
		return LHzp;
	}
	public void setLHzp(double hzp) {
		LHzp = hzp;
	}
	public double getLHzbcj() {
		return LHzbcj;
	}
	public void setLHzbcj(double hzbcj) {
		LHzbcj = hzbcj;
	}
	public double getLHss() {
		return LHss;
	}
	public void setLHss(double hss) {
		LHss = hss;
	}
	public double getLHsm() {
		return LHsm;
	}
	public void setLHsm(double hsm) {
		LHsm = hsm;
	}
	public double getLH05() {
		return LH05;
	}
	public void setLH05(double lh05) {
		LH05 = lh05;
	}
	public double getLH02() {
		return LH02;
	}
	public void setLH02(double lh02) {
		LH02 = lh02;
	}
	public double getLHxzb() {
		return LHxzb;
	}
	public void setLHxzb(double hxzb) {
		LHxzb = hxzb;
	}
	public double getLHzd() {
		return LHzd;
	}
	public void setLHzd(double hzd) {
		LHzd = hzd;
	}
	public double getLHkf() {
		return LHkf;
	}
	public void setLHkf(double hkf) {
		LHkf = hkf;
	}
	public double getLHwjg() {
		return LHwjg;
	}
	public void setLHwjg(double hwjg) {
		LHwjg = hwjg;
	}
	public double getLHqt() {
		return LHqt;
	}
	public void setLHqt(double hqt) {
		LHqt = hqt;
	}
	public double getSKYWYShouldwxy() {
		return SKYWYShouldwxy;
	}
	public void setSKYWYShouldwxy(double shouldwxy) {
		SKYWYShouldwxy = shouldwxy;
	}
	public double getSKYWYFactwxy() {
		return SKYWYFactwxy;
	}
	public void setSKYWYFactwxy(double factwxy) {
		SKYWYFactwxy = factwxy;
	}
	public double getSKYWYShouldyar() {
		return SKYWYShouldyar;
	}
	public void setSKYWYShouldyar(double shouldyar) {
		SKYWYShouldyar = shouldyar;
	}
	public double getSKYWYFactyar() {
		return SKYWYFactyar;
	}
	public void setSKYWYFactyar(double factyar) {
		SKYWYFactyar = factyar;
	}
	public double getSKYWYShouldcgz() {
		return SKYWYShouldcgz;
	}
	public void setSKYWYShouldcgz(double shouldcgz) {
		SKYWYShouldcgz = shouldcgz;
	}
	public double getSKYWYFactcgz() {
		return SKYWYFactcgz;
	}
	public void setSKYWYFactcgz(double factcgz) {
		SKYWYFactcgz = factcgz;
	}
	public double getSKYWYShouldsyp() {
		return SKYWYShouldsyp;
	}
	public void setSKYWYShouldsyp(double shouldsyp) {
		SKYWYShouldsyp = shouldsyp;
	}
	public double getSKYWYFactsyp() {
		return SKYWYFactsyp;
	}
	public void setSKYWYFactsyp(double factsyp) {
		SKYWYFactsyp = factsyp;
	}
	public double getSKYWYShouldsjw() {
		return SKYWYShouldsjw;
	}
	public void setSKYWYShouldsjw(double shouldsjw) {
		SKYWYShouldsjw = shouldsjw;
	}
	public double getSKYWYFactsjw() {
		return SKYWYFactsjw;
	}
	public void setSKYWYFactsjw(double factsjw) {
		SKYWYFactsjw = factsjw;
	}
	public double getSKYWYShouldwbj() {
		return SKYWYShouldwbj;
	}
	public void setSKYWYShouldwbj(double shouldwbj) {
		SKYWYShouldwbj = shouldwbj;
	}
	public double getSKYWYFactwbj() {
		return SKYWYFactwbj;
	}
	public void setSKYWYFactwbj(double factwbj) {
		SKYWYFactwbj = factwbj;
	}
	public double getLHYWYShouldwxy() {
		return LHYWYShouldwxy;
	}
	public void setLHYWYShouldwxy(double shouldwxy) {
		LHYWYShouldwxy = shouldwxy;
	}
	public double getLHYWYFactwxy() {
		return LHYWYFactwxy;
	}
	public void setLHYWYFactwxy(double factwxy) {
		LHYWYFactwxy = factwxy;
	}
	public double getLHYWYShouldyar() {
		return LHYWYShouldyar;
	}
	public void setLHYWYShouldyar(double shouldyar) {
		LHYWYShouldyar = shouldyar;
	}
	public double getLHYWYFactyar() {
		return LHYWYFactyar;
	}
	public void setLHYWYFactyar(double factyar) {
		LHYWYFactyar = factyar;
	}
	public double getLHYWYShouldcgz() {
		return LHYWYShouldcgz;
	}
	public void setLHYWYShouldcgz(double shouldcgz) {
		LHYWYShouldcgz = shouldcgz;
	}
	public double getLHYWYFactcgz() {
		return LHYWYFactcgz;
	}
	public void setLHYWYFactcgz(double factcgz) {
		LHYWYFactcgz = factcgz;
	}
	public double getLHYWYShouldsyp() {
		return LHYWYShouldsyp;
	}
	public void setLHYWYShouldsyp(double shouldsyp) {
		LHYWYShouldsyp = shouldsyp;
	}
	public double getLHYWYFactsyp() {
		return LHYWYFactsyp;
	}
	public void setLHYWYFactsyp(double factsyp) {
		LHYWYFactsyp = factsyp;
	}
	public double getLHYWYShouldsjw() {
		return LHYWYShouldsjw;
	}
	public void setLHYWYShouldsjw(double shouldsjw) {
		LHYWYShouldsjw = shouldsjw;
	}
	public double getLHYWYFactsjw() {
		return LHYWYFactsjw;
	}
	public void setLHYWYFactsjw(double factsjw) {
		LHYWYFactsjw = factsjw;
	}
	public double getLHYWYShouldwbj() {
		return LHYWYShouldwbj;
	}
	public void setLHYWYShouldwbj(double shouldwbj) {
		LHYWYShouldwbj = shouldwbj;
	}
	public double getLHYWYFactwbj() {
		return LHYWYFactwbj;
	}
	public void setLHYWYFactwbj(double factwbj) {
		LHYWYFactwbj = factwbj;
	}
	
	
	
	

}
