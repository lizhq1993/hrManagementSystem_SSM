package domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Employee implements Serializable {

	private static final long serialVersionUID = 8668523702321530975L;
	
	private Integer id;
	private Dept dept;
	private Job job;
	private String name;
	private String cardId;
	private String address;
	private String postCode;
	private String tel;
	private String phone;
	private String qqNum;
	private String email;
	private Integer sex;
	private String party;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	private String race;
	private String education;
	private String speciality;
	private String hobby;
	private String remark;
	private Date createDate;
	
	public Employee(){};
	
	
	
	public Employee(Integer id, Dept dept, Job job, String name, String cardId, String address, String postCode,
			String tel, String phone, String qqNum, String email, Integer sex, String party, Date birthday, String race,
			String education, String speciality, String hobby, String remark, Date createDate) {
		super();
		this.id = id;
		this.dept = dept;
		this.job = job;
		this.name = name;
		this.cardId = cardId;
		this.address = address;
		this.postCode = postCode;
		this.tel = tel;
		this.phone = phone;
		this.qqNum = qqNum;
		this.email = email;
		this.sex = sex;
		this.party = party;
		this.birthday = birthday;
		this.race = race;
		this.education = education;
		this.speciality = speciality;
		this.hobby = hobby;
		this.remark = remark;
		this.createDate = createDate;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQqNum() {
		return qqNum;
	}
	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", dept=" + dept + ", job=" + job + ", name=" + name + ", cardId=" + cardId
				+ ", address=" + address + ", postCode=" + postCode + ", tel=" + tel + ", phone=" + phone + ", qqNum="
				+ qqNum + ", email=" + email + ", sex=" + sex + ", party=" + party + ", birthday=" + birthday
				+ ", race=" + race + ", education=" + education + ", speciality=" + speciality + ", hobby=" + hobby
				+ ", remark=" + remark + ", createDate=" + createDate + "]";
	}
	
	
}
