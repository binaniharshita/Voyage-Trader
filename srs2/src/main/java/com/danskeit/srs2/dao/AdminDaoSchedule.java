package com.danskeit.srs2.dao;

import java.sql.Date;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.danskeit.srs2.bean.RouteBean;
import com.danskeit.srs2.bean.ScheduleBean;
import com.danskeit.srs2.bean.ShipBean;



@Repository
public class AdminDaoSchedule {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public String createSchedule(String shipid, String routeid,Date startDate)
	{
		Session s=sessionFactory.openSession();
		Criteria crit = s.createCriteria(ShipBean.class);
		crit.add(Restrictions.eq("shipid",shipid));	
		ShipBean resultShip = (ShipBean) crit.list().get(0);
		
		Criteria crit1 = s.createCriteria(RouteBean.class);
		crit1.add(Restrictions.eq("routeid",routeid));	
		RouteBean resultRoute = (RouteBean) crit1.list().get(0);
		ScheduleBean sb = new ScheduleBean();
		sb.setShip(resultShip);
		sb.setRoute(resultRoute);
		sb.setStartdate(startDate);
		Transaction t=s.beginTransaction();
			
			String temp = resultRoute.getSource().substring(0,2) + resultRoute.getDestination().substring(0, 2);
			temp = temp.toUpperCase();
			sb.setScheduleid(temp);
			s.save(sb);
			s.close();
			t.commit();
			return "SUCCESS";
		
		
		
	}
	
	public ArrayList<ScheduleBean> viewAllSchedule()
	{
		Session s=sessionFactory.openSession();
		Criteria crit = s.createCriteria(ScheduleBean.class);				
		ArrayList<ScheduleBean> results = (ArrayList<ScheduleBean>) crit.list();
		s.close();
		return results;
		
	}
	
	public ArrayList<ScheduleBean> viewScheduleById(String id)
	{
		Session s=sessionFactory.openSession();		
		Criteria crit = s.createCriteria(ScheduleBean.class);
		crit.add(Restrictions.eq("scheduleid",id));	
		ArrayList<ScheduleBean> results = (ArrayList<ScheduleBean>) crit.list();
		s.close();
		return results;
		
	}
	public void modSchedule(String shipid,String scheduleid,String routeid,Date startdate)
	{		
		
		Session s=sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		ScheduleBean sb=s.get(ScheduleBean.class, scheduleid);
		Criteria crit = s.createCriteria(ShipBean.class);
		crit.add(Restrictions.eq("shipid",shipid));	
		ShipBean resultShip = (ShipBean) crit.list().get(0);
		
		Criteria crit1 = s.createCriteria(RouteBean.class);
		crit1.add(Restrictions.eq("routeid",routeid));	
		RouteBean resultRoute = (RouteBean) crit1.list().get(0);
		
		sb.setRoute(resultRoute);
		sb.setShip(resultShip);
		sb.setStartdate(startdate);
		s.saveOrUpdate(sb);
		s.close();
		t.commit();
		
	}
	
	public int removeSchedule(String id)
	{
		Session s=sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		ScheduleBean sb=s.get(ScheduleBean.class, id);
        if(sb!=null){
           s.delete(sb);
         
        t.commit();
        return 1;
        }
        return 0;
	}
}
