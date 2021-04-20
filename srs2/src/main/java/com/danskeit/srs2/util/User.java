package com.danskeit.srs2.util;


import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import com.danskeit.srs2.bean.*;
import com.danskeit.srs2.util.Authentication;

@Repository
public class User {
	@Autowired
	private Authentication auth;
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public String login(CredentialsBean cb)
	{
		
		if(auth.authenticate(cb))
		{
			auth.changeLoginStatus(cb, 1);
			return auth.authorize(cb.getUserid());
		}
		else
			return "INVALID";
	}
	
	
	public String register(ProfileBean pbean,CredentialsBean cb,String firstname)
	{
		Session s=sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		String temp = firstname.substring(0,2);
		temp = temp.toUpperCase();
		pbean.setUserid(temp);
		s.saveOrUpdate(pbean);
		s.persist(pbean);
		cb.setLoginstatus(0);
		cb.setPassword(pbean.getPassword());
		cb.setUserid(pbean.getUserid());
		cb.setUsertype("C");
		System.out.println(pbean);
		s.save(cb);
		s.close();
		t.commit();
		return "Registered";
	}
	public String returnId(String emailid)
	{
		Session s1=sessionFactory.openSession();
		Criteria crit1 = s1.createCriteria(ProfileBean.class);
		crit1.add(Restrictions.eq("emailid",emailid));	
		ArrayList<ProfileBean> results = (ArrayList<ProfileBean>) crit1.list();
		s1.close();
		return results.get(0).getUserid();
	}
	public boolean logout(String userid)
	{
		Session s=sessionFactory.openSession();
		CredentialsBean cb=s.get(CredentialsBean.class, userid);
		s.close();
		return auth.changeLoginStatus(cb, 0);
	}
	public String changePassword(String id, String newPassword)
	{
		Session s=sessionFactory.openSession();		
		Transaction t=s.beginTransaction();
		CredentialsBean cb1=s.get(CredentialsBean.class, id);
		cb1.setPassword(newPassword);
		s.update(cb1);
		s.close();
		t.commit();
		return "SUCCESS";
	}
}
