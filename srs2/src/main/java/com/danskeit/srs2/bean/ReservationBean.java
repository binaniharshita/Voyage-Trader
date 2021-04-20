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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.danskeit.srs2.*;

@Entity
@Table(name="srs_tbl_Reservation")
public class ReservationBean {
	@Id
	private String reservationid;
	
	public CredentialsBean getCredentials() {
		return credentials;
	}

	public void setCredentials(CredentialsBean credentials) {
		this.credentials = credentials;
	}
	@Column
	private Date bookingdate;
	@Column
	private Date journeydate;
	@Column
	private int noofseats;
	@Column
	private double totalfare;
	@Column
	private String bookingstatus;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="scheduleid")  
	private ScheduleBean schedules;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userid")  
	private CredentialsBean credentials;
	
	@OneToMany(mappedBy = "reservation",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
	private List<PassengerBean> passengers = new ArrayList<PassengerBean>();
	
	
	
	public List<PassengerBean> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerBean> passengers) {
		this.passengers = passengers;
	}

	
	public ScheduleBean getSchedules() {
		return schedules;
	}

	public void setSchedules(ScheduleBean schedules) {
		this.schedules = schedules;
	}
	  
	public ReservationBean()
	{
		setReservationid("OK");
	}
	
	

	
	/*public RouteBean getRoute() {
		return route;
	}
	public void setRoute(RouteBean route) {
		this.route = route;
	}*/
	
	public String getReservationid() {
		return reservationid;
	}
	public void setReservationid(String i) {
		//String temp = "RE";
		int max = 1999;
		int min = 1000;
		int id = (int) Math.floor(Math.random()*(max-min+1)+min);
		String temp = i + String.valueOf(id);
		this.reservationid = temp;
	}
	/*public String getScheduleid() {
		return scheduleid;
	}
	public void setScheduleid(String scheduleid) {
		this.scheduleid = scheduleid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}*/
	public Date getBookingdate() {
		return bookingdate;
	}
	public void setBookingdate(Date bookingdate) {
		this.bookingdate = bookingdate;
	}
	public Date getJourneydate() {
		return journeydate;
	}
	public void setJourneydate(Date journeydate) {
		this.journeydate = journeydate;
	}
	public int getNoofseats() {
		return noofseats;
	}
	public void setNoofseats(int noofseats) {
		this.noofseats = noofseats;
	}
	public double getTotalfare() {
		return totalfare;
	}
	public void setTotalfare(double totalfare) {
		this.totalfare = totalfare;
	}
	public String getBookingstatus() {
		return bookingstatus;
	}
	public void setBookingstatus(String bookingstatus) {
		this.bookingstatus = bookingstatus;
	}

}
