package com.danskeit.srs2.bean;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="srs_tbl_User_Credentials")
public class CredentialsBean {
	@Id
	private String userid;
	@Column
	private String password;
	@Column
	private String usertype;
	@Column
	private int loginstatus;
	
	@OneToMany(mappedBy = "credentials",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReservationBean> reservations = new ArrayList<ReservationBean>();
	
	public List<ReservationBean> getReservations() {
		return reservations;
	}
	public void setReservations(List<ReservationBean> reservations) {
		this.reservations = reservations;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public int getLoginstatus() {
		return loginstatus;
	}
	public void setLoginstatus(int loginstatus) {
		this.loginstatus = loginstatus;
	}
	
	
}
