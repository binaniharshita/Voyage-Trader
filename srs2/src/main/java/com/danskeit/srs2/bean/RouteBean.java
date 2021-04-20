package com.danskeit.srs2.bean;

import java.util.*;
import java.math.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="srs_tbl_route")
public class RouteBean {
@Id
private String routeid;
@Column(length=20)
private String source;
@Column(length=20)
private String destination;
@Column(length=10)
private String travelduration;
@Column(length=6)
private int fare;


public List<ScheduleBean> getSchedules() {
	return schedules;
}



public void setSchedules(List<ScheduleBean> schedules) {
	this.schedules = schedules;
}
@OneToMany(mappedBy = "route",cascade = CascadeType.ALL, orphanRemoval = true)
private List<ScheduleBean> schedules = new ArrayList<ScheduleBean>();
public String getRouteid() {
	return routeid;
}



RouteBean()
{
	setRouteid("OK");
}
public void setRouteid(String i) {
	//String temp = "RO";
	int max = 1999;
	int min = 1000;
	int id = (int) Math.floor(Math.random()*(max-min+1)+min);
	String temp = i + String.valueOf(id);
	this.routeid = temp;
}
public String getSource() {
	return source;
}
public void setSource(String source) {
	this.source = source;
}
public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public String getTravelduration() {
	return travelduration;
}
public void setTravelduration(String travelduration) {
	this.travelduration = travelduration;
}
public int getFare() {
	return fare;
}
public void setFare(String fare) {
	this.fare = Integer.parseInt(fare);
}


}
