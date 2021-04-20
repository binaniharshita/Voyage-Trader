package com.danskeit.srs2.bean;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name="srs_table_passenger")
public class PassengerBean implements Serializable{

@Id
@Column
private String name;

public ReservationBean getReservations() {
	return reservation;
}
public void setReservation(ReservationBean reservation) {
	this.reservation = reservation;
}
public ScheduleBean getSchedule() {
	return schedule;
}
public void setSchedule(ScheduleBean schedule) {
	this.schedule = schedule;
}
@Column
private String age;
@Column
private String gender;

public ReservationBean getReservation() {
	return reservation;
}
@Id
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name="reservationid")  
private ReservationBean reservation;

@Id
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name="scheduleid")  
private ScheduleBean schedule;

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAge() {
	return age;
}
public void setAge(String age) {
	this.age = age;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}


}
