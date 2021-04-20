package com.danskeit.srs2.bean;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="srs_tbl_credit_card")
public class CreditCardBean {
	@Id
	private String creditcardnumber;
	@Column
	private Date validfrom;
	@Column
	private Date validto;
	@Column
	private double balance;
	@Column
	private String userid;
	public String getCreditcardnumber() {
		return creditcardnumber;
	}
	public void setCreditcardnumber(String creditcardnumber) {
		this.creditcardnumber = creditcardnumber;
	}
	public Date getValidfrom() {
		return validfrom;
	}
	public void setValidfrom(Date validfrom) {
		this.validfrom = validfrom;
	}
	public Date getValidto() {
		return validto;
	}
	public void setValidto(Date validto) {
		this.validto = validto;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(String bal) {
		this.balance = Double.parseDouble(bal);
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}
