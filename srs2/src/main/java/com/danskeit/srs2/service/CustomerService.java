package com.danskeit.srs2.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danskeit.srs2.bean.*;
import com.danskeit.srs2.dao.*;


@Service
public class CustomerService {
	@Autowired
	private CustomerDao cdao;
	@Transactional
	public double getTotalFare(String scheduleid,int noofseats)
	{
		return cdao.getTotalFare(scheduleid,noofseats);
	}
	public ArrayList<ScheduleBean> viewScheduleByRoute(String source,String destination,Date date)
	{
		return cdao.viewScheduleByRoute(source, destination, date);
	}
	public String makeReservation(ReservationBean rb,List<PassengerBean> pblist,String cardnumber,String id,String scheduleid)
	{
		return cdao.makeReservation(rb,pblist,cardnumber,id,scheduleid);
	}
	public Map<ReservationBean,List<PassengerBean>> viewReservationById(String id)
	{
		return cdao.viewReservationById(id);
	}
	public int removeReservation(String id)
	{
		return cdao.removeReservation(id);
	}
	
	public void newCard(CreditCardBean ccb)
	{
		cdao.newCard(ccb);
	}
	public ArrayList<ReservationBean> viewAllReservation()
	{
		return cdao.viewAllReservation();
	}
	
	/*public boolean returnReservationSeats(String id,int noofseats)
	{
		return returnReservationSeats(id,noofseats);
	
	}*/
	
	public CreditCardBean getCardDetails(String id)
	{
		return cdao.getCardDetails(id);
	}
	public String updateCardDetails(CreditCardBean ccb)
	{
		return cdao.updateCardDetails(ccb);
	
	}

}
