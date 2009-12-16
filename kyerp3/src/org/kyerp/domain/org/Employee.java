package org.kyerp.domain.org;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Employee implements Serializable {
	private static final long		serialVersionUID	= -6545298577393000755L;

	@Id
	@GeneratedValue
	private long					id;
	@OneToOne
	private User					user;
	@OneToMany(mappedBy = "employee")
	private List<EmployeeResume>	resume				= new ArrayList<EmployeeResume>();
	@OneToMany(mappedBy = "employee")
	private List<EmployeeFamily>	family				= new ArrayList<EmployeeFamily>();
	@OneToOne
	private Department				companyDept;
	private long					empNo;

	private String					realname;
	private String					email;
	private String					totp;
	private String					bz;
	private Integer					sex;
	private String					insurance;
	private Date					birthday;
	private String					nativeplace;
	private Integer					rprtype;
	private String					nation;
	private String					health;
	private String					idcard;
	private String					polity;
	private String					weeding;
	private String					school;
	private Date					graduteDate;
	private String					degree;
	private String					speciality;
	private String					interest;
	private String					address;
	private String					tel;
	private String					mobile;
	private String					rpraddress;
	private String					rprtel;
	private Date					participateDate;
	private String					photo;
	private String					remark;
	private String					workTel;
	private Integer					qq;
	private String					msn;
	private String					selfDesc;
	private String					zhiChen;
	private Date					zhiChenDate;
	private String					bianZhi;
	private String					disabilityIdcard;
	private Date					contractSignDate;
	private Date					contractMatureDate;
	private String					contractRemark;
	private Integer					infoStatus;
	private Integer					workStatus;
	private String					rewardsAndPunishiment;
	private String					jobChanges;
	private String					workTrain;
	private String					recognizor;
	private String					physical;

	public Employee() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public Integer getRprtype() {
		return rprtype;
	}

	public void setRprtype(Integer rprtype) {
		this.rprtype = rprtype;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<EmployeeResume> getResume() {
		return resume;
	}

	public void setResume(List<EmployeeResume> resume) {
		this.resume = resume;
	}

	public List<EmployeeFamily> getFamily() {
		return family;
	}

	public void setFamily(List<EmployeeFamily> family) {
		this.family = family;
	}

	public long getEmpNo() {
		return empNo;
	}

	public void setEmpNo(long empNo) {
		this.empNo = empNo;
	}

	public Department getCompanyDept() {
		return companyDept;
	}

	public void setCompanyDept(Department companyDept) {
		this.companyDept = companyDept;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTotp() {
		return totp;
	}

	public void setTotp(String totp) {
		this.totp = totp;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNativeplace() {
		return nativeplace;
	}

	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPolity() {
		return polity;
	}

	public void setPolity(String polity) {
		this.polity = polity;
	}

	public String getWeeding() {
		return weeding;
	}

	public void setWeeding(String weeding) {
		this.weeding = weeding;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Date getGraduteDate() {
		return graduteDate;
	}

	public void setGraduteDate(Date graduteDate) {
		this.graduteDate = graduteDate;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRpraddress() {
		return rpraddress;
	}

	public void setRpraddress(String rpraddress) {
		this.rpraddress = rpraddress;
	}

	public String getRprtel() {
		return rprtel;
	}

	public void setRprtel(String rprtel) {
		this.rprtel = rprtel;
	}

	public Date getParticipateDate() {
		return participateDate;
	}

	public void setParticipateDate(Date participateDate) {
		this.participateDate = participateDate;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWorkTel() {
		return workTel;
	}

	public void setWorkTel(String workTel) {
		this.workTel = workTel;
	}

	public Integer getQq() {
		return qq;
	}

	public void setQq(Integer qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getSelfDesc() {
		return selfDesc;
	}

	public void setSelfDesc(String selfDesc) {
		this.selfDesc = selfDesc;
	}

	public String getZhiChen() {
		return zhiChen;
	}

	public void setZhiChen(String zhiChen) {
		this.zhiChen = zhiChen;
	}

	public Date getZhiChenDate() {
		return zhiChenDate;
	}

	public void setZhiChenDate(Date zhiChenDate) {
		this.zhiChenDate = zhiChenDate;
	}

	public String getBianZhi() {
		return bianZhi;
	}

	public void setBianZhi(String bianZhi) {
		this.bianZhi = bianZhi;
	}

	public String getDisabilityIdcard() {
		return disabilityIdcard;
	}

	public void setDisabilityIdcard(String disabilityIdcard) {
		this.disabilityIdcard = disabilityIdcard;
	}

	public Date getContractSignDate() {
		return contractSignDate;
	}

	public void setContractSignDate(Date contractSignDate) {
		this.contractSignDate = contractSignDate;
	}

	public Date getContractMatureDate() {
		return contractMatureDate;
	}

	public void setContractMatureDate(Date contractMatureDate) {
		this.contractMatureDate = contractMatureDate;
	}

	public String getContractRemark() {
		return contractRemark;
	}

	public void setContractRemark(String contractRemark) {
		this.contractRemark = contractRemark;
	}

	public Integer getInfoStatus() {
		return infoStatus;
	}

	public void setInfoStatus(Integer infoStatus) {
		this.infoStatus = infoStatus;
	}

	public Integer getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(Integer workStatus) {
		this.workStatus = workStatus;
	}

	public String getRewardsAndPunishiment() {
		return rewardsAndPunishiment;
	}

	public void setRewardsAndPunishiment(String rewardsAndPunishiment) {
		this.rewardsAndPunishiment = rewardsAndPunishiment;
	}

	public String getJobChanges() {
		return jobChanges;
	}

	public void setJobChanges(String jobChanges) {
		this.jobChanges = jobChanges;
	}

	public String getWorkTrain() {
		return workTrain;
	}

	public void setWorkTrain(String workTrain) {
		this.workTrain = workTrain;
	}

	public String getRecognizor() {
		return recognizor;
	}

	public void setRecognizor(String recognizor) {
		this.recognizor = recognizor;
	}

	public String getPhysical() {
		return physical;
	}

	public void setPhysical(String physical) {
		this.physical = physical;
	}

}
