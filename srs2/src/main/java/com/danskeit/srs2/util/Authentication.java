package com.danskeit.srs2.util;

import java.util.ArrayList;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.danskeit.srs2.bean.*;

@Repository
public class Authentication {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/*Authentication*/
	public boolean authenticate(CredentialsBean cb)
	{		
		Session s=sessionFactory.openSession();		
		Criteria crit = s.createCriteria(CredentialsBean.class);
		crit.add(Restrictions.eq("userid",cb.getUserid()));	
		crit.add(Restrictions.eq("password",cb.getPassword()));	
		ArrayList<CredentialsBean> results = (ArrayList<CredentialsBean>) crit.list();
		s.close();
		if(results.size()==0)
			return false;
		else 
			return true;		
	}
	
	public String authorize(String id)
	{
		Session s=sessionFactory.openSession();		
		Criteria crit = s.createCriteria(CredentialsBean.class);
		crit.add(Restrictions.eq("userid",id));	
		ArrayList<CredentialsBean> results = (ArrayList<CredentialsBean>) crit.list();
		s.close();
		return results.get(0).getUsertype();
	}
	
	public boolean changeLoginStatus(CredentialsBean cb,int loginStatus)
	{
		if(cb!=null)
		{
			Session s=sessionFactory.openSession();		
			Transaction t=s.beginTransaction();
			CredentialsBean cb1=s.get(CredentialsBean.class, cb.getUserid());
			cb1.setLoginstatus(loginStatus);
			s.saveOrUpdate(cb1);
			s.close();
			t.commit();
			return true;
		}
		return false;
	}
}
