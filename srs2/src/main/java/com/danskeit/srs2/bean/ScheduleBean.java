package com.danskeit.srs2.bean;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="srs_tbl_Schedule")
public class ScheduleBean {
@Id
private String scheduleid;

public RouteBean getRoute() {
	return route;
}
public void setRoute(RouteBean route) {
	this.route = route;
}
@Column
private Date startdate;
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name="routeid")  
private RouteBean route;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name="shipid")  
private ShipBean ship;


@OneToMany(mappedBy = "schedules",cascade = CascadeType.ALL, orphanRemoval = true)
private List<ReservationBean> reservations = new ArrayList<ReservationBean>();


@OneToMany(mappedBy = "schedule",cascade = CascadeType.ALL, orphanRemoval = true)
private List<PassengerBean> passengers = new ArrayList<PassengerBean>();
public List<PassengerBean> getPassengers() {
	return passengers;
}



public void setPassengers(List<PassengerBean> passengers) {
	this.passengers = passengers;
}
public List<ReservationBean> getReservations() {
	return reservations;
}
public void setReservations(List<ReservationBean> reservations) {
	this.reservations = reservations;
}
public ScheduleBean()
{
	setScheduleid("OK");
}
public ShipBean getShip() {
	return ship;
}
public void setShip(ShipBean ship) {
	this.ship = ship;
}
public String getScheduleid() {
	return scheduleid;
}
public void setScheduleid(String i) {
	//String temp = "SC";
	int max = 1999;
	int min = 1000;
	int id = (int) Math.floor(Math.random()*(max-min+1)+min);
	String temp = i + String.valueOf(id);
	this.scheduleid = temp;
}

public Date getStartdate() {
	return startdate;
}
public void setStartdate(Date startdate) {
	this.startdate = startdate;
}
}
