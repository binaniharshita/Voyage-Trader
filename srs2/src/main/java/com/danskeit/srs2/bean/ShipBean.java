package com.danskeit.srs2.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Value;


@Entity
@Table(name="srs_tbl_Ship")
public class ShipBean {
	@Id
	@Column(name="shipid", length=6)
	private String shipid;
	@Column(name="shipname", length=15)
	private String shipname;
	@Column(name="seatingcapacity", length=4)
	private int seatingcapacity;
	@Column(name="reservationcapacity", length=4)
	private int reservationcapacity;
	
	
	@OneToMany(mappedBy = "ship",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduleBean> schedules = new ArrayList<ScheduleBean>();
	
	public List<ScheduleBean> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<ScheduleBean> schedules) {
		this.schedules = schedules;
	}
	ShipBean()
	{
		setShipid("OK");
	}
	public String getShipid() {
		return shipid;
	}
	public void setShipid(String i) {
		
		int max = 1999;
		int min = 1000;
		int id = (int) Math.floor(Math.random()*(max-min+1)+min);
		String temp = i + String.valueOf(id);
		this.shipid = temp;
	}
	public String getShipname() {
		return shipname;
	}
	public void setShipname(String shipname) {
		this.shipname = shipname;
	}
	public int getSeatingcapacity() {
		return seatingcapacity;
	}
	public void setSeatingcapacity(String seatingcapacity) {
		this.seatingcapacity = Integer.parseInt(seatingcapacity);
	}
	public int getReservationcapacity() {
		return reservationcapacity;
	}
	public void setReservationcapacity(String reservationcapacity) {
		this.reservationcapacity = Integer.parseInt(reservationcapacity);
	}
	@Override
	public String toString() {
		return "ShipBean [shipid=" + shipid + ", shipname=" + shipname + ", seatingcapacity=" + seatingcapacity + ", reservationcapacity="
				+ reservationcapacity + "]";
	}
}
