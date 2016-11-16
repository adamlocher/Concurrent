package com.systems.concurrent.ejb.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.tomcat.util.codec.binary.Base64;

@Entity
@Table(name = "USER")
public class UserData extends AbstractData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5061392331814032973L;

	public interface AccounTypes {
		final String ADMIN = "Admin";
		final String PM = "PM";
		final String DEVELOPER = "Developer";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USR_ID")
	private Long id;

	@Column(name = "USR_NAME")
	private String name;

	@Column(name = "USR_SURNAME")
	private String surname;

	@Column(name = "USR_PASSWORD")
	private String password;

	@Column(name = "USR_EMAIL")
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USR_BIRTHDAY")
	private Date birthday;

	@Column(name = "USR_ACC_TYPE")
	private String accType;

	@Column(name = "USR_PHONE_NUMBER")
	private String phone;

	@Column(name = "USR_SKYPE")
	private String skype;

	@Column(name = "USR_LOGO")
	private byte[] logo;

	@Column(name = "USR_SKILLS")
	private String skills;

	@Column(name = "USR_APPROVED")
	private Boolean approved = Boolean.FALSE;
	
	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
	private List<ProjectData> projects = new ArrayList<>();

	public List<ProjectData> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectData> projects) {
		this.projects = projects;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return name + " " + surname + " " + email + " " + birthday + " " + password;
	}

	public String getImage() {
		if (logo != null)
			return Base64.encodeBase64String(logo);
		return "";
	}
	
	public String getToString(){
		return  " "+name+" "+surname;
	}
}
