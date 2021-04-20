package com.danskeit.srs2.service;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danskeit.srs2.bean.ScheduleBean;
import com.danskeit.srs2.bean.ShipBean;
import com.danskeit.srs2.dao.AdminDaoSchedule;


@Service
public class AdminServiceSchedule {
@Autowired
private AdminDaoSchedule adao;
@Transactional

public String addSchedule(String shipid, String routeid, Date startdate)
{
	return adao.createSchedule(shipid,routeid,startdate);
}

public ArrayList<ScheduleBean> viewAllSchedule()
{
	return adao.viewAllSchedule();
}
public ArrayList<ScheduleBean> viewScheduleById(String id)
{
	return adao.viewScheduleById(id);
	
}
public ArrayList<ScheduleBean> modScheduleById(String id)
{
	return adao.viewScheduleById(id);
	
}
public void modSchedule(String shipid,String scheduleid,String routeid,Date startdate)
{
	adao.modSchedule(shipid,scheduleid,routeid,startdate);
}
public int removeSchedule(String id)
{
	 return adao.removeSchedule(id);
	
}
}
