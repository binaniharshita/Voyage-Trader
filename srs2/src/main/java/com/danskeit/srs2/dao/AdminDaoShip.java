package com.danskeit.srs2.dao;

import java.sql.Date;
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
public class AdminDaoShip {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/*Ships*/
	public String createShip(ShipBean sb)
	{
		System.out.println(sb);
		if(sb!=null)
		{
			Session s=sessionFactory.openSession();
			Transaction t=s.beginTransaction();
			String temp = sb.getShipname().substring(0,2);
			temp = temp.toUpperCase();
			sb.setShipid(temp);
			s.save(sb);
			s.close();
			t.commit();
			return "SUCCESS";
		}
		
		return "FAIL";
	}
	
	public ArrayList<ShipBean> viewAllShips()
	{
		Session s=sessionFactory.openSession();
		Criteria crit = s.createCriteria(ShipBean.class);				
		ArrayList<ShipBean> results = (ArrayList<ShipBean>) crit.list();
		s.close();
		return results;
		
	}
	
	
	public ShipBean viewShipById(String id)
	{
		Session s=sessionFactory.openSession();		
		Criteria crit = s.createCriteria(ShipBean.class);
		crit.add(Restrictions.eq("shipid",id));	
		ArrayList<ShipBean> results = (ArrayList<ShipBean>) crit.list();
		return results.get(0);
		
	}
	public int removeShip(String id)
	{
		Session s=sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		ShipBean sb=s.get(ShipBean.class, id);
		System.out.print("Hello");
        if(sb!=null){
           s.delete(sb);
           t.commit();
          return 1;
        }       
       
        return 0;
	}
	
	public void modShip(String shipid,String shipname,int seatingcapacity,int reservationcapacity)
	{		
		Session s=sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		Query q=s.createQuery("update ShipBean set shipname=:shipname,seatingcapacity=:seatingcapacity,reservationcapacity=:reservationcapacity where shipid=:shipid");
		q.setParameter("shipid", shipid);
		q.setParameter("shipname", shipname);
		q.setParameter("reservationcapacity", reservationcapacity);
		q.setParameter("seatingcapacity", seatingcapacity);
		q.executeUpdate();
		s.close();
		t.commit();
		
	}

	public ArrayList<PassengerBean> viewReservationDetails()
	{
		Session s=sessionFactory.openSession();
		Criteria crit = s.createCriteria(PassengerBean.class);				
		ArrayList<PassengerBean> results = (ArrayList<PassengerBean>) crit.list();
		s.close();
		return results;
	}

	public ArrayList<ProfileBean> viewPassengerDetails()
	{
		Session s=sessionFactory.openSession();
		Criteria crit = s.createCriteria(ProfileBean.class);				
		ArrayList<ProfileBean> results = (ArrayList<ProfileBean>) crit.list();
		s.close();
		return results;
	}
	
}
