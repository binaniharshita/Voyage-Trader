package com.danskeit.srs2.bean;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="srs_tbl_User_Profile")
public class ProfileBean {
	@Id
	@Column(name="userid", length=6)
	private String userid;
	@Column(name="firstname")
	private String firstname;
	@Column(name="lastname")
	private String lastname;
	@Column(name="dateofbirth")
	private Date dateofbirth;
	@Column(name="gender")
	private String gender;
	@Column(name="street")
	private String street;
	@Column(name="location")
	private String location;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="pincode")
	private String pincode;
	@Column(name="mobileno")
	private String mobileno;
	@Column(name="emailid")
	private String emailid;
	private String password;
	
	ProfileBean()
	{
		setUserid("OK");
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String i) {
		int max = 1999;
		int min = 1000;
		int id = (int) Math.floor(Math.random()*(max-min+1)+min);
		String temp = i + String.valueOf(id);
		this.userid = temp;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	@Override
	public String toString() {
		return "ProfileBean [userid=" + userid + ", firstname=" + firstname + ", lastname=" + lastname + ", dateofbirth="
				+ dateofbirth + ", gender=" + gender + ", street=" + street + ", location=" + location + ", city=" + city
				+ ", state=" + state + ", pincode=" + pincode + ", mobileno=" + mobileno + ", emailid=" + emailid
				+ ", password=" + password + "]";
	}
	
}
