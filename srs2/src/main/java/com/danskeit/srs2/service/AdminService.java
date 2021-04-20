package com.danskeit.srs2.service;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danskeit.srs2.bean.*;
import com.danskeit.srs2.dao.*;


@Service
public class AdminService {
@Autowired
private AdminDaoShip adao;
@Transactional
public String addShip(ShipBean sb)
{
	return adao.createShip(sb);
}
public ArrayList<ShipBean> viewAllShips()
{
	return adao.viewAllShips();
}
public ShipBean viewShipById(String id)
{
	return adao.viewShipById(id);
	
}
public int removeShip(String id)
{
	 return adao.removeShip(id);
	
}
public ShipBean modShipById(String id)
{
	return adao.viewShipById(id);
	
}
public void modShip(String shipid,String shipname,int seatingcapacity,int reservationcapacity)
{
	adao.modShip(shipid,shipname,seatingcapacity,reservationcapacity);
}

public ArrayList<PassengerBean> viewReservationDetails()
{
	return adao.viewReservationDetails();
}
public ArrayList<ProfileBean> viewPassengerDetails()
{
	return adao.viewPassengerDetails();
}

}
