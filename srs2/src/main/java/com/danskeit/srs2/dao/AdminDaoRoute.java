package com.danskeit.srs2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.danskeit.srs2.bean.*;


@Repository
public class AdminDaoRoute {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	
	/*Routes*/
	public String createRoute(RouteBean rb)
	{
		System.out.println(rb);
		if(rb!=null)
		{
			Session s=sessionFactory.openSession();
			Transaction t=s.beginTransaction();
			String temp = rb.getSource().substring(0,2) + rb.getDestination().substring(0, 2);
			temp = temp.toUpperCase();
			rb.setRouteid(temp);
			s.save(rb);
			s.close();
			t.commit();
			return "SUCCESS";
		}
		
		return "FAIL";
	}
	public ArrayList<RouteBean> viewAllRoutes()
	{
		Session s=sessionFactory.openSession();
		
		Criteria crit = s.createCriteria(RouteBean.class);
		//crit.add(Restrictions.eq("shipid",id));	
		ArrayList<RouteBean> results = (ArrayList<RouteBean>) crit.list();
		s.close();
		return results;
		
	}
	
	
	public ArrayList<RouteBean> viewRouteById(String id)
	{
		Session s=sessionFactory.openSession();
		
		Criteria crit = s.createCriteria(RouteBean.class);
		crit.add(Restrictions.eq("routeid",id));	
		ArrayList<RouteBean> results = (ArrayList<RouteBean>) crit.list();
		s.close();
		return results;
		
	}
	public int removeRoute(String id)
	{
		System.out.print(id);
		Session s=sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		RouteBean rb=s.get(RouteBean.class, id);
		System.out.print(rb.getRouteid());
        if(rb!=null){       	
    		s.delete(rb); 
    		t.commit();
    		return 1;
        }
        
        return 0;
        
	}
	
	public void modRoute(String routeid,String source,String destination ,int fare,String travelduration)
	{		
		Session s=sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		Query q=s.createQuery("update RouteBean set source=:source,destination=:destination,fare=:fare,travelduration=:travelduration where routeid=:routeid");
		q.setParameter("routeid", routeid);
		q.setParameter("source", source);
		q.setParameter("destination",destination);
		q.setParameter("fare", fare);
		q.setParameter("travelduration", travelduration);
		q.executeUpdate();
		s.close();
		t.commit();
		
	}
}
