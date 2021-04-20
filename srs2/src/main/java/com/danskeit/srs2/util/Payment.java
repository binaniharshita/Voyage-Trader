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
public class Payment {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	boolean findByCardNumber(String userid,String cardnumber)
	{
		Session s=sessionFactory.openSession();		
		Criteria crit = s.createCriteria(CreditCardBean.class);
		crit.add(Restrictions.eq("userid",userid));	
		crit.add(Restrictions.eq("creditcardnumber",cardnumber));
		ArrayList<CreditCardBean> al = (ArrayList<CreditCardBean>) crit.list();
		s.close();
		if(al.size()==0)
			return false;
		else
			return true;
	}
	public String process(String userid,String cardnumber,double amt)
	{
		if(!findByCardNumber(userid,cardnumber))
			return "INVALID CREDIT CARD NUMBER";
		Session s=sessionFactory.openSession();		
		Transaction t=s.beginTransaction();
		Criteria crit = s.createCriteria(CreditCardBean.class);
		crit.add(Restrictions.eq("userid",userid));	
		crit.add(Restrictions.eq("creditcardnumber",cardnumber));
		ArrayList<CreditCardBean> al = (ArrayList<CreditCardBean>) crit.list();
		CreditCardBean ccb = al.get(0);
		System.out.print(ccb.getBalance());
		if(amt > ccb.getBalance())
			return "INSUFFICIENT FUNDS";
		else
		{
			double bal = ccb.getBalance();
			bal = bal - amt;
			System.out.print(bal);
			ccb.setBalance(String.valueOf(bal));
			s.update(ccb);
			s.close();
			t.commit();
			return "SUCCESS";
		}
	}
}
