package com.tyopf.service.Valuation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.tyopf.vo.AfBase;
import com.tyopf.vo.AfDispose;
import com.tyopf.vo.AfElement;
import com.tyopf.vo.AfValuation;

public class CalSK {

	@SuppressWarnings("unchecked")
	public List<AfValuation> calSK(AfBase af) {

		// AfBase af = (AfBase) afDAO.getAFById(afId);
		if (af != null) {

			AfValuation CoverPSbanFee = new AfValuation(); // 封面PS版费
			AfValuation CovershangbanFee = new AfValuation(); // 封面上版费
			AfValuation CoverPressbanFee = new AfValuation(); // 封面上版费
			AfValuation CoverPaperFee = new AfValuation(); // 封面用纸费
			AfValuation BBpingbanFee = new AfValuation(); // 正文拼版费
			AfValuation BB_PSFee = new AfValuation(); // 正文PS版费
			AfValuation BB_ShangFee = new AfValuation(); // 正文PS版费
			AfValuation BB_PrintFee = new AfValuation(); // 正文印刷费
			AfValuation BBPaperFee = new AfValuation(); // 正文用纸费

			AfValuation BB_BindingFee = new AfValuation(); // 装订费
			AfValuation CI_PS_Fee = new AfValuation(); // 插页PS费
			AfValuation CI_ShangFee = new AfValuation(); // 插页上板费
			AfValuation CI_PrintFee = new AfValuation(); // 插页印刷费
			AfValuation CIPaperFee = new AfValuation(); // 插页用纸费

			// 计算过程中使用的参数
			double PSbanFee = 60.0; // ps 版费单价
			double shangbanFee = 0; // 上版费
			int Cover_ColorAmount = 0; // 封面颜色数
			int ColorAmount = 0; // 封面颜色数
			// double Cover_PressDanjia = 510; // 封面印刷系数
			double Cover_PressDanjia = 360; // 封面印刷系数
			double CoverPressDanjia2 = 140; // 封面印刷系数2
			int BB_Plate_Amont = 0; // 正文版数
			int CI_Plate_Amont = 0; // 插页版数
			String PEdition = af.getEdition(); // 印次
			if (null == PEdition || "".equals(PEdition)) {
				PEdition = "1-0";
			}
			double KPA = new Double(af.getAmount()) / 1000;// 千印数
			if (KPA < 2) {
				KPA = 2; // 千印数最小为2
			}
			double BB_Plate_pk = 15; // 正文拼版费
			double BB_PS_fee = 48; // 正文ＰＳ版费
			double BB_Shangban_fee = 12; // 正文上版费

			// 计算过程中使用的项目(金额)
			double CoverPSban_Fee = 0; // 封面PS版费
			double Covershangban_Fee = 0; // 封面上版费
			double CoverPress_Fee = 0; // 封面上版费
			double BB_BPlate_fee = 0; // 正文拼版费
			double BB_PS_Fee = 0; // 正文PS版费
			double BB_Shang_Fee = 0; // 正文上版费
			double qmdDanjia = 0.1; // 骑马订单价

			double BB_Print_fee = 0; // 正文印刷费
			double BB_pk = 9.5; // 正文千印单价

			double EPS = 0.0; // 印张
			double Binding_fee = 0;
			double binding_pks = 0.029; // 千印单价

			List<AfValuation> bjList = new ArrayList();

			Set afes = af.getAfElement();
			Set afds = af.getAfDispose();
			if (afes != null) {

				for (Iterator iterator = afes.iterator(); iterator.hasNext();) {
					AfElement afe = (AfElement) iterator.next();

					if (afe.getEType().equals("Cover")) { // 计算封面的费用
						Cover_ColorAmount = afe.getEColorBackN()
								+ afe.getEColorFrontN();
						if ("05".equals(afe.getEMachine())
								|| "双面".equals(afe.getEMachine())) {
							shangbanFee = 12;
						}
						if ("四色".equals(afe.getEMachine())) {
							shangbanFee = 8;
						}

						CoverPSban_Fee = (PSbanFee - shangbanFee)
								* Cover_ColorAmount;
						double CoverPSban_danjia = PSbanFee - shangbanFee;
						CoverPSbanFee = new AfValuation();
						CoverPSbanFee.setItemName("封面PS版费");
						CoverPSbanFee.setChejian("制版车间");
						if ("02".equals(afe.getEMachine())) {
							BBpingbanFee.setChejian("小制版");
						}
						CoverPSbanFee.setDanjia(CoverPSban_danjia);
						CoverPSbanFee.setCalProcess(CoverPSban_danjia + "x"
								+ Cover_ColorAmount);
						CoverPSbanFee.setTotalAmount(com.tyopf.util.MathTools
								.round(CoverPSban_Fee, 2));
						bjList.add(CoverPSbanFee);

						Covershangban_Fee = shangbanFee * Cover_ColorAmount;

						CovershangbanFee = new AfValuation();
						CovershangbanFee.setItemName("封面上版费");
						CovershangbanFee.setChejian(afe.getEMachine());
						CovershangbanFee.setDanjia(shangbanFee);
						CovershangbanFee.setCalProcess(shangbanFee + "x"
								+ Cover_ColorAmount);
						CovershangbanFee
								.setTotalAmount(com.tyopf.util.MathTools.round(
										Covershangban_Fee, 2));
						bjList.add(CovershangbanFee);

						String CoverPressProcess = "";

						if (Cover_ColorAmount < 4 && Cover_ColorAmount > 0) {
							CoverPress_Fee = Cover_PressDanjia
									- CoverPressDanjia2
									* (4 - Cover_ColorAmount);
							CoverPressProcess = Cover_PressDanjia + "-"
									+ CoverPressDanjia2 + "x" + "(4-"
									+ Cover_ColorAmount + ")";
						}
						if (Cover_ColorAmount == 4) {
							CoverPress_Fee = Cover_PressDanjia;
							CoverPressProcess = Cover_PressDanjia + "";
						}
						if (Cover_ColorAmount > 4) {
							CoverPress_Fee = Cover_PressDanjia
									+ CoverPressDanjia2
									* (Cover_ColorAmount - 4);
							CoverPressProcess = Cover_PressDanjia + "+"
									+ CoverPressDanjia2 + "x" + "("
									+ Cover_ColorAmount + "-" + "4)";
						}
						// 超过5令,每增加1令增加100块钱
						double Paper_Ream = new Double(afe.getEReam());
						if (Paper_Ream > 5) {
							CoverPress_Fee = CoverPress_Fee + (Paper_Ream - 5)
									* 100;
							CoverPressProcess = CoverPressProcess + "+"
									+ (Paper_Ream - 5) + "x" + 100;
						}

						CoverPressbanFee = new AfValuation();
						CoverPressbanFee.setItemName("封面印刷费");
						CoverPressbanFee.setChejian(afe.getEMachine());
						CoverPressbanFee.setDanjia(Cover_PressDanjia);
						CoverPressbanFee.setCalProcess(CoverPressProcess);
						CoverPressbanFee
								.setTotalAmount(com.tyopf.util.MathTools.round(
										CoverPress_Fee, 2));
						bjList.add(CoverPressbanFee);

						if ("厂料".equals(afe.getEPaperFrom())) {
							CoverPaperFee = new AfValuation();
							CoverPaperFee.setItemName("封面用纸费");
							CoverPaperFee.setChejian("库房");
							CoverPaperFee.setDanjia(0);
							CoverPaperFee.setCalProcess("");
							CoverPaperFee.setTotalAmount(0);
							bjList.add(CoverPaperFee);
						}
						if ("现购".equals(afe.getEPaperFrom())) {
							CoverPaperFee = new AfValuation();
							CoverPaperFee.setItemName("封面用纸费");
							CoverPaperFee.setChejian("库房");
							CoverPaperFee.setDanjia(0);
							CoverPaperFee.setCalProcess("");
							CoverPaperFee.setTotalAmount(0);
							bjList.add(CoverPaperFee);
						}
					}

					if (afe.getEType().equals("BB")) { // 计算正文的费用
						BB_Plate_Amont = new Integer(afe.getEPlateAmount()); // 版数

						// 计算正文拼版费
						int PEdition_n = PEdition.length();
						if (PEdition_n > 2
								&& "-1".equals(PEdition.substring(
										PEdition_n - 2, PEdition_n))) {
							if (af.getFormat().matches("(?i).*16.*")) {
								BB_Plate_pk = 15;
							}
							if (af.getFormat().matches("(?i).*32.*")) {
								BB_Plate_pk = 20;
							}

							// ("(?i).*i am.*");
							BB_BPlate_fee = BB_Plate_Amont * BB_Plate_pk; // 计算正文拼版费
							BBpingbanFee = new AfValuation();
							BBpingbanFee.setItemName("正文拼版费");
							BBpingbanFee.setChejian("制版车间");
							if ("02".equals(afe.getEMachine())) {
								BBpingbanFee.setChejian("小制版");
							}
							BBpingbanFee.setDanjia(BB_Plate_pk);
							BBpingbanFee.setCalProcess(BB_Plate_pk + "x"
									+ BB_Plate_Amont);
							BBpingbanFee
									.setTotalAmount(com.tyopf.util.MathTools
											.round(BB_BPlate_fee, 2));
							bjList.add(BBpingbanFee);
						}

						BB_PS_Fee = BB_Plate_Amont * BB_PS_fee; // 计算正文PS版费

						BB_PSFee = new AfValuation();
						BB_PSFee.setItemName("正文PS版费");
						BB_PSFee.setChejian("制版车间");
						if ("02".equals(afe.getEMachine())) {
							BBpingbanFee.setChejian("小制版");
						}
						BB_PSFee.setDanjia(BB_PS_fee);
						BB_PSFee
								.setCalProcess(BB_PS_fee + "x" + BB_Plate_Amont);
						BB_PSFee.setTotalAmount(com.tyopf.util.MathTools.round(
								BB_PS_Fee, 2));
						bjList.add(BB_PSFee);

						BB_Shang_Fee = BB_Plate_Amont * BB_Shangban_fee; // 计算正文上版费

						BB_ShangFee = new AfValuation();
						BB_ShangFee.setItemName("正文上版费");
						BB_ShangFee.setChejian(afe.getEMachine());
						BB_ShangFee.setDanjia(BB_Shangban_fee);
						BB_ShangFee.setCalProcess(BB_Shangban_fee + "x"
								+ BB_Plate_Amont);
						BB_ShangFee.setTotalAmount(com.tyopf.util.MathTools
								.round(BB_Shang_Fee, 2));
						bjList.add(BB_ShangFee);

						// 计算正文印刷费
						EPS = new Double(afe.getEPs()); // 印张

						double pks = 1.2; // 纸张系数
						if (afe.getESs().substring(0, 3).equals("787")) {
							pks = 1;
						}

						if ("05".equals(afe.getEMachine())) {
							BB_pk = 12.5;
						}
						if ("双面".equals(afe.getEMachine())) {
							BB_pk = 9.5;
						}
						ColorAmount = Cover_ColorAmount = afe.getEColorBackN()
								+ afe.getEColorFrontN();
						;

						BB_Print_fee = EPS * KPA * BB_pk * pks * ColorAmount;

						BB_PrintFee = new AfValuation();
						BB_PrintFee.setItemName("正文印刷费");
						BB_PrintFee.setChejian(afe.getEMachine());
						BB_PrintFee.setDanjia(BB_pk);
						BB_PrintFee.setCalProcess("" + EPS + "x" + KPA + "x"
								+ BB_pk + "x" + pks + "x" + ColorAmount);
						BB_PrintFee.setTotalAmount(com.tyopf.util.MathTools
								.round(BB_Print_fee, 2));
						bjList.add(BB_PrintFee);

						if ("厂料".equals(afe.getEPaperFrom())) {
							BBPaperFee = new AfValuation();
							BBPaperFee.setItemName("正文用纸费");
							BBPaperFee.setChejian("库房");
							BBPaperFee.setDanjia(0);
							BBPaperFee.setCalProcess("");
							BBPaperFee.setTotalAmount(0);
							bjList.add(BBPaperFee);
						}
						if ("现购".equals(afe.getEPaperFrom())) {
							BBPaperFee = new AfValuation();
							BBPaperFee.setItemName("正文用纸费");
							BBPaperFee.setChejian("库房");
							BBPaperFee.setDanjia(0);
							BBPaperFee.setCalProcess("");
							BBPaperFee.setTotalAmount(0);
							bjList.add(BBPaperFee);
						}
					}

					if (afe.getEType().equals("CI")) { // 计算插页费用
						CI_Plate_Amont = new Integer(afe.getEPlateAmount());
						double CI_PS_fee = 0;
						if ("05".equals(afe.getEMachine())
								|| "双面".equals(afe.getEMachine())) {
							CI_PS_fee = 48;
						}
						if ("四色".equals(afe.getEMachine())) {
							CI_PS_fee = 52;
						}

						double CI_PSFee = CI_Plate_Amont * CI_PS_fee; // 计算插页PS版费

						CI_PS_Fee = new AfValuation();
						CI_PS_Fee.setItemName("插页PS版费");
						CI_PS_Fee.setChejian("制版车间");
						if ("02".equals(afe.getEMachine())) {
							BBpingbanFee.setChejian("小制版");
						}
						CI_PS_Fee.setDanjia(CI_PS_fee);
						CI_PS_Fee.setCalProcess(CI_PS_fee + "x"
								+ CI_Plate_Amont);
						CI_PS_Fee.setTotalAmount(com.tyopf.util.MathTools
								.round(CI_PSFee, 2));
						bjList.add(CI_PS_Fee);

						double CI_shangPS_fee = 0;
						if ("05".equals(afe.getEMachine())
								|| "双面".equals(afe.getEMachine())) {
							CI_shangPS_fee = 12;
						}
						if ("四色".equals(afe.getEMachine())) {
							CI_shangPS_fee = 8;
						}
						double CI_Shang_Fee = CI_Plate_Amont * CI_shangPS_fee; // 计算插页上版费

						CI_ShangFee = new AfValuation();
						CI_ShangFee.setItemName("插页上版费");
						CI_ShangFee.setChejian(afe.getEMachine());
						CI_ShangFee.setDanjia(CI_shangPS_fee);
						CI_ShangFee.setCalProcess(CI_shangPS_fee + "x"
								+ CI_Plate_Amont);
						CI_ShangFee.setTotalAmount(com.tyopf.util.MathTools
								.round(CI_Shang_Fee, 2));
						bjList.add(CI_ShangFee);

						if (afe.getEColorFrontN() == 1) {

							double CI_Print_Fee = 38; // 计算插页印刷费

							CI_PrintFee = new AfValuation();
							CI_PrintFee.setItemName("插页印刷费");
							CI_PrintFee.setChejian(afe.getEMachine());
							CI_PrintFee.setDanjia(38);
							CI_PrintFee.setCalProcess("38");
							CI_PrintFee.setTotalAmount(com.tyopf.util.MathTools
									.round(CI_Print_Fee, 2));
							bjList.add(CI_PrintFee);
						}
						if (afe.getEColorFrontN() == 4) {

							double CI_Print_Fee = 400; // 计算插页印刷费

							// 超过5令,每增加1令增加100块钱
							String CIPressProcess = "400";
							double Paper_Ream = new Double(afe.getEReam());
							if (Paper_Ream > 5) {
								CI_Print_Fee = CI_Print_Fee + (Paper_Ream - 5)
										* 100;
								CIPressProcess = CIPressProcess + "+"
										+ (Paper_Ream - 5) + "x" + 100;
							}

							CI_PrintFee = new AfValuation();
							CI_PrintFee.setItemName("插页印刷费");
							CI_PrintFee.setChejian(afe.getEMachine());
							CI_PrintFee.setDanjia(400);
							CI_PrintFee.setCalProcess(CIPressProcess);
							CI_PrintFee.setTotalAmount(com.tyopf.util.MathTools
									.round(CI_Print_Fee, 2));
							bjList.add(CI_PrintFee);
						}

						if ("厂料".equals(afe.getEPaperFrom())) {
							CIPaperFee = new AfValuation();
							CIPaperFee.setItemName("插页用纸费");
							CIPaperFee.setChejian("库房");
							CIPaperFee.setDanjia(0);
							CIPaperFee.setCalProcess("");
							CIPaperFee.setTotalAmount(0);
							bjList.add(CIPaperFee);
						}
						if ("现购".equals(afe.getEPaperFrom())) {
							CIPaperFee = new AfValuation();
							CIPaperFee.setItemName("插页用纸费");
							CIPaperFee.setChejian("库房");
							CIPaperFee.setDanjia(0);
							CIPaperFee.setCalProcess("");
							CIPaperFee.setTotalAmount(0);
							bjList.add(CIPaperFee);
						}

					}

				}
			}

			if (afds != null) {
				for (Iterator iterator1 = afds.iterator(); iterator1.hasNext();) {
					AfDispose afd = (AfDispose) iterator1.next();
					if ("本厂".equals(afd.getAfDFactory())) {
						if (afd.getAfDItem().equals("胶订")) {

							if (af.getFormat().matches("(?i).*16.*")) { // 16k胶装费用
								if (KPA <= 10) {
									binding_pks = 0.029;
								}
								if (KPA > 10 && KPA <= 30) {
									binding_pks = 0.027;
								}
								if (KPA > 30) {
									binding_pks = 0.02;
								}
							}
							if (af.getFormat().matches("(?i).*32.*")) { // 32k胶装费用
								if (KPA <= 10) {
									binding_pks = 0.035;
								}
								if (KPA > 10 && KPA <= 30) {
									binding_pks = 0.033;
								}
								if (KPA > 30) {
									binding_pks = 0.03;
								}
							}
							Binding_fee = binding_pks * KPA * 1000 * EPS;

							BB_BindingFee = new AfValuation();
							BB_BindingFee.setItemName("正文装订费");
							BB_BindingFee.setChejian("装订");
							BB_BindingFee.setDanjia(binding_pks);
							BB_BindingFee.setCalProcess("" + binding_pks + "x"
									+ KPA + "x" + "1000" + "x" + EPS);
							BB_BindingFee
									.setTotalAmount(com.tyopf.util.MathTools
											.round(Binding_fee, 2));
							bjList.add(BB_BindingFee);
						}
						if (afd.getAfDItem().equals("骑马订")) {
							Binding_fee = qmdDanjia * KPA * 1000;
							BB_BindingFee = new AfValuation();
							BB_BindingFee.setItemName("正文装订费");
							BB_BindingFee.setChejian("装订");
							BB_BindingFee.setDanjia(binding_pks);
							BB_BindingFee.setCalProcess("" + qmdDanjia + "x"
									+ KPA + "x1000");
							BB_BindingFee
									.setTotalAmount(com.tyopf.util.MathTools
											.round(Binding_fee, 2));
							bjList.add(BB_BindingFee);
						}
					}
				}
			}

// Total_fee = CoverPSbanFee.getTotalAmount()
// + CovershangbanFee.getTotalAmount()
// + CoverPressbanFee.getTotalAmount()
// + BBpingbanFee.getTotalAmount() + BB_PSFee.getTotalAmount()
// + BB_ShangFee.getTotalAmount()
// + BB_PrintFee.getTotalAmount()
// + BB_BindingFee.getTotalAmount()
// + CI_PS_Fee.getTotalAmount() + CI_ShangFee.getTotalAmount()
// + CI_PrintFee.getTotalAmount() + 0; // 总费用
//
// Totalfee = new AfValuation();
// Totalfee.setItemName("总金额");
// Totalfee.setDanjia(0);
// Totalfee.setTotalAmount(Total_fee);
// bjList.add(Totalfee);

			return bjList;
		}
		List<AfValuation> bjList = new ArrayList();
		bjList.add(new AfValuation());
		bjList.add(new AfValuation());
		bjList.add(new AfValuation());
		bjList.add(new AfValuation());
		bjList.add(new AfValuation());
		bjList.add(new AfValuation());
		return bjList;

	}
}
