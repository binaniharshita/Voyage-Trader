package com.danskeit.srs2.service;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danskeit.srs2.bean.*;
import com.danskeit.srs2.dao.AdminDaoRoute;


@Service
public class AdminServiceRoute {
@Autowired
private AdminDaoRoute adao;
@Transactional

public String addRoute(RouteBean rb)
{
	return adao.createRoute(rb);
}
public ArrayList<RouteBean> viewAllRoutes()
{
	return adao.viewAllRoutes();
}
public ArrayList<RouteBean> viewRouteById(String id)
{
	return adao.viewRouteById(id);
	
}
public int removeRoute(String id)
{
	 return adao.removeRoute(id);
	
}
public ArrayList<RouteBean> modByIdRoute(String id)
{
	return adao.viewRouteById(id);
	
}
public void modRoute(String routeid,String source,String destination ,int fare,String travelduration)
{		

	adao.modRoute(routeid,source,destination,fare,travelduration);
}

}
