package com.danskeit.srs2.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.danskeit.srs2.bean.*;
import com.danskeit.srs2.util.*;

@Repository
public class CustomerDao {
	@Autowired 
	Payment pay;
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	public double getTotalFare(String scheduleid,int noofseats)
	{
		Session s=sessionFactory.openSession();
		Criteria scheduleCriteria = s.createCriteria(ScheduleBean.class);
		scheduleCriteria.add(Restrictions.eq("scheduleid", scheduleid));
		ArrayList<ScheduleBean> results=(ArrayList<ScheduleBean>)scheduleCriteria.list();
		String id=results.get(0).getRoute().getRouteid();
		System.out.println(id);
		Criteria routeCriteria = s.createCriteria(RouteBean.class);
		routeCriteria.add(Restrictions.eq("routeid", id));
		ArrayList<RouteBean> results1 = (ArrayList<RouteBean>)routeCriteria.list();
		int cap = results.get(0).getShip().getReservationcapacity();
		if(cap<noofseats)
			return 0;
		s.close();
		if(results1.size()==0)
			return 0;
		return (noofseats*results1.get(0).getFare());
	}
	
	public ArrayList<ScheduleBean> viewScheduleByRoute(String source,String destination,Date date)
	{
		Session s=sessionFactory.openSession();
		Criteria scheduleCriteria = s.createCriteria(ScheduleBean.class);
		scheduleCriteria.add(Restrictions.eq("startdate", date));
		Criteria routeCriteria = scheduleCriteria.createCriteria("route");
		routeCriteria.add(Restrictions.eq("source", source));
		routeCriteria.add(Restrictions.eq("destination", destination));
		Criteria shipCriteria = scheduleCriteria.createCriteria("ship");
		shipCriteria.add(Restrictions.ne("reservationcapacity", 0));
		ArrayList<ScheduleBean> results = (ArrayList<ScheduleBean>)scheduleCriteria.list();
		s.close();
		return results;
	}
	public String makeReservation(ReservationBean rb,List<PassengerBean> pblist,String cardnumber,String userid, String scheduleid)
	{
		Session s=sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		rb.setBookingstatus("1");
		
		Criteria credentialCriteria = s.createCriteria(CredentialsBean.class);
		credentialCriteria.add(Restrictions.eq("userid", userid));
		CredentialsBean cb = (CredentialsBean) credentialCriteria.list().get(0);
		
		Criteria scheduleCriteria = s.createCriteria(ScheduleBean.class);
		scheduleCriteria.add(Restrictions.eq("scheduleid", scheduleid));
		ScheduleBean sb = (ScheduleBean) scheduleCriteria.list().get(0);
		
		rb.setCredentials(cb);
		rb.setSchedules(sb);
		
		String temp = sb.getRoute().getSource().substring(0,2) + sb.getRoute().getDestination().substring(0, 2);
		temp = temp.toUpperCase();
		rb.setReservationid(temp);
		//rb.setPassengers(pblist);
		s.saveOrUpdate(rb);
		
		int size = pblist.size();
		System.out.println("Hellooo"+size);
		double amt = rb.getTotalfare(); 
		
		
		String status = pay.process(userid,cardnumber, amt);
			System.out.println(status);
			
			if(status.equals("SUCCESS"))
			{
				for(int i=0;i<size;i++)
				{
					PassengerBean pb = pblist.get(i);
					pb.setReservation(rb);
					pb.setSchedule(sb);
					s.save(pb);
				}
				
			}
		
		
		
		t.commit();
		s.close();
		
		changeReservationCapacity(rb.getSchedules().getScheduleid(),size);
		return status;
	}
	public void changeReservationCapacity(String id, int size)
	{
		Session s=sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		Criteria scheduleCriteria = s.createCriteria(ScheduleBean.class);
		scheduleCriteria.add(Restrictions.eq("scheduleid", id));
		System.out.println(id+" " + size);
		String shipid = (String)((ScheduleBean) scheduleCriteria.list().get(0)).getShip().getShipid();
		Criteria shipCriteria = s.createCriteria(ShipBean.class);
		shipCriteria.add(Restrictions.eq("shipid", shipid));
		ArrayList<ShipBean> al = (ArrayList<ShipBean>) shipCriteria.list();
		ShipBean sb = al.get(0);
		int cap = sb.getReservationcapacity();
		cap = cap - size;
		sb.setReservationcapacity(String.valueOf(cap));
		s.update(sb);
		s.close();
		t.commit();
	}
	public Map<ReservationBean,List<PassengerBean>> viewReservationById(String id)
	{
		Map<ReservationBean, List<PassengerBean>> mp = new HashMap<ReservationBean,List<PassengerBean>>();
		Session s=sessionFactory.openSession();
		Criteria reservationCriteria = s.createCriteria(ReservationBean.class);
		reservationCriteria.add(Restrictions.eq("reservationid", id));
		ReservationBean rb = (ReservationBean) reservationCriteria.list().get(0);
		/*Criteria passengerCriteria = s.createCriteria(PassengerBean.class);
		passengerCriteria.add(Restrictions.eq("reservation", rb));
		List<PassengerBean> results = (List<PassengerBean>)passengerCriteria.list();*/
		List<PassengerBean> results = rb.getPassengers();
		mp.put(rb,results);
		s.close();
		return mp;
	}
	
	public int removeReservation(String id)
	{
		Session s=sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		Criteria reservationCriteria = s.createCriteria(ReservationBean.class);
		reservationCriteria.add(Restrictions.eq("reservationid", id));
		ReservationBean rb = (ReservationBean) reservationCriteria.list().get(0);
		
		String userid = rb.getCredentials().getUserid();
		double amt = rb.getTotalfare();
		Criteria crit = s.createCriteria(CreditCardBean.class);
		crit.add(Restrictions.eq("userid",userid));	
		ArrayList<CreditCardBean> al = (ArrayList<CreditCardBean>) crit.list();
		CreditCardBean ccb = al.get(0);
		amt = amt + ccb.getBalance();
		ccb.setBalance(String.valueOf(amt));
		
		
		int size = rb.getNoofseats() * (-1);
        if(rb!=null){
        	
           s.delete(rb);
          s.update(ccb);
           t.commit();
           changeReservationCapacity(rb.getSchedules().getScheduleid(),size);
          return 1;
        }       
        
        return 0;
	}
	
	public void newCard(CreditCardBean ccb)
	{
		Session s=sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		s.save(ccb);
		s.close();
		t.commit();
	}
	
	
	
	
	public ArrayList<ReservationBean> viewAllReservation()
	{
		Session s=sessionFactory.openSession();
		
		Criteria crit = s.createCriteria(ReservationBean.class);
		crit.setResultTransformer( DistinctRootEntityResultTransformer.INSTANCE );
		//crit.add(Restrictions.eq("shipid",id));	
		ArrayList<ReservationBean> results = (ArrayList<ReservationBean>) crit.list();
		
		System.out.println(results.size()+" okay");
		s.close();
		return results;
	}
	
	
	public CreditCardBean getCardDetails(String id)
	{
		Session s=sessionFactory.openSession();
		
		CreditCardBean ccb = s.get(CreditCardBean.class, id);
		s.close();
		return ccb;
		
	}
	
	public String updateCardDetails(CreditCardBean ccb)
	{
		Session s=sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		s.saveOrUpdate(ccb);
		s.close();
		t.commit();
		return "Update Successful!";
	}
	
	
}
