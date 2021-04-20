package com.danskeit.srs2.controller;

import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.danskeit.srs2.bean.*;
import com.danskeit.srs2.util.User;
import com.danskeit.srs2.service.*;

@RestController
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private User auth;
	@Autowired
	private AdminService aserv;
	@Autowired
	private AdminServiceRoute aservr;
	@Autowired
	private AdminServiceSchedule aservs;
	
	@RequestMapping("/ViewPass")
	public ModelAndView ViewPass(Model m)
	{
		ArrayList<ProfileBean> al = aserv.viewPassengerDetails();
		if(al.size() == 0)
		{
			m.addAttribute("message", "No customer records found");
			return new ModelAndView("ShipsAdmin/SUCCESS");
		}
		m.addAttribute("passenger",al);
		return new ModelAndView("View Passenger Details");
	}
@RequestMapping("/addship")
public ModelAndView addShip()
{
	return new ModelAndView("ShipsAdmin/AddShip");
}
@RequestMapping("/newShip")
public ModelAndView newShip(@ModelAttribute("sb")ShipBean sb,Model m)
{
	String check = aserv.addShip(sb);
	if(check.equals("SUCCESS"))
	{
		m.addAttribute("message", "Add Ship Successful");
		return new ModelAndView("ShipsAdmin/SUCCESS");
	}
		
	return new ModelAndView("Welcome");
}
@RequestMapping("/viewallship")
public ModelAndView viewAllShips(HttpSession hs,HttpServletRequest req,Model m)
{
	hs=req.getSession();
	ArrayList<ScheduleBean> sb = aservs.viewAllSchedule();
	ArrayList<ShipBean> sal = aserv.viewAllShips();
	if(sal == null)
	{
		m.addAttribute("message", "No ship records found");
		return new ModelAndView("ShipsAdmin/SUCCESS");
	}
	hs.setAttribute("aaa", sal);
return new ModelAndView("ShipsAdmin/ViewAll");
}
@RequestMapping("/viewshipid")
public ModelAndView meth9()
{
	System.out.println("Here I am");
	return new ModelAndView("ShipsAdmin/IdEntry");
}
@RequestMapping("/viewById")
public ModelAndView meth10(@RequestParam("shipid") String id,ModelMap m)
{
	//hs=req.getSession();
	ShipBean sb = aserv.viewShipById(id);
	if(sb==null)
	{
		m.addAttribute("message","No ship for this id found");
		return new ModelAndView("ShipsAdmin/SUCCESS");
	}
	m.addAttribute("selectedShip", sb);
	return new ModelAndView("ShipsAdmin/ViewById");
}
@RequestMapping("/deleteship")
public ModelAndView meth12(Model m)
{
	ArrayList<ShipBean> sal = aserv.viewAllShips();
	if(sal == null)
	{
		m.addAttribute("message", "No ship records found");
		return new ModelAndView("ShipsAdmin/SUCCESS");
	}
	m.addAttribute("aaa", sal);
	return new ModelAndView("ShipsAdmin/IdEntryDel");
}
@RequestMapping("/delById")
public ModelAndView delByIdShip(@RequestParam("shipid") String id,Model m)
{
	if(aserv.removeShip(id)==1)
	{
		m.addAttribute("message", "Delete Ship Successful");
		return new ModelAndView("ShipsAdmin/SUCCESS");
	}
	else
		return new ModelAndView("ShipsAdmin/IdEntryDel");
}
@RequestMapping("/modifyship")
public ModelAndView meth13(Model m)
{
	
	ArrayList<ScheduleBean> sb = aservs.viewAllSchedule();
	ArrayList<ShipBean> sal = aserv.viewAllShips();
	if(sal == null)
	{
		m.addAttribute("message", "No ship records found");
		return new ModelAndView("ShipsAdmin/SUCCESS");
	}
	m.addAttribute("aaa", sal);
	return new ModelAndView("ShipsAdmin/IdEntryModShip");
}
@RequestMapping("/modById")
public ModelAndView meth14(@RequestParam(value = "shipid")  String id,ModelMap m)
{
	System.out.println(id);
	ShipBean sb = aserv.viewShipById(id);
	if(sb==null)
	{
		m.addAttribute("message","No ship for this id found");
		return new ModelAndView("ShipsAdmin/SUCCESS");
	}
	m.addAttribute("selectedShipModify", sb);
	return new ModelAndView("ShipsAdmin/ModifyShipLanding");
}
@RequestMapping("/editShip")
public ModelAndView meth15(@RequestParam("shipid") String shipid,@RequestParam("shipname") String shipname,@RequestParam("seatingcapacity") String seatingcapacity,@RequestParam("reservationcapacity") String reservationcapacity, Model m)
{
	//if(aserv.modShip(sb))
	aserv.modShip(shipid,shipname,Integer.parseInt(seatingcapacity),Integer.parseInt(reservationcapacity));
		m.addAttribute("message", "Modify Ship Successful");
		return new ModelAndView("ShipsAdmin/SUCCESS");
	//else
		//return new ModelAndView("ShipsAdmin/IdEntryModShip");
}


@RequestMapping("/route")
public ModelAndView RouteLanding()
{
	return new ModelAndView("RouteAdmin/RouteLanding");
}
@RequestMapping("/addroute")
public ModelAndView AddRoute()
{
	return new ModelAndView("RouteAdmin/AddRoute");
}
@RequestMapping("/newRoute")
public ModelAndView newRoute(@ModelAttribute("rb")RouteBean rb,Model m)
{
	String check = aservr.addRoute(rb);
	if(check.equals("SUCCESS"))
	{
		m.addAttribute("message", "Add Route Successful");
		return new ModelAndView("RouteAdmin/SUCCESS");
	}
	return new ModelAndView("Welcome");
}
@RequestMapping("/viewallroute")
public ModelAndView viewAllRoute(HttpSession hs,HttpServletRequest req,Model m)
{
	System.out.println("Hello");
	hs=req.getSession();
	ArrayList<RouteBean> alr = aservr.viewAllRoutes();
	if(alr==null)
	{
		m.addAttribute("message", "No routes found");
		return new ModelAndView("RouteAdmin/SUCCESS");
	}
	hs.setAttribute("aaa", alr);
return new ModelAndView("RouteAdmin/ViewAllRoutes");
}

@RequestMapping("/viewRouteid")
public ModelAndView viewRouteid()
{
	return new ModelAndView("RouteAdmin/IdEntry");
}
@RequestMapping("/viewByIdRoute")
public ModelAndView viewByIdRoute(@RequestParam("routeid") String id,HttpSession hs,HttpServletRequest req,Model m)
{
	hs=req.getSession();
	ArrayList<RouteBean> alr = aservr.viewRouteById(id);
	if(alr==null)
	{
		m.addAttribute("message", "No routes found for this id");
		return new ModelAndView("RouteAdmin/SUCCESS");
	}
	hs.setAttribute("aab",alr );
	return new ModelAndView("RouteAdmin/ViewById");
}
@RequestMapping("/deleteRoute")
public ModelAndView deleteRoute(HttpSession hs,HttpServletRequest req,Model m)
{
	hs=req.getSession();
	ArrayList<RouteBean> alr = aservr.viewAllRoutes();
	if(alr==null)
	{
		m.addAttribute("message", "No routes found");
		return new ModelAndView("RouteAdmin/SUCCESS");
	}
	hs.setAttribute("aaa", alr);
	return new ModelAndView("RouteAdmin/IdEntryDel");
}
@RequestMapping("/delByIdRoute")
public ModelAndView delByIdRoute(@RequestParam("routeid") String id,Model m)
{
	if(aservr.removeRoute(id)==1)
	{
		m.addAttribute("message", "Delete Route Successful");
		return new ModelAndView("RouteAdmin/SUCCESS");
	}
	else
		return new ModelAndView("RouteAdmin/IdEntryDel");
}
@RequestMapping("/modifyRoute")
public ModelAndView modifyRoute(HttpSession hs,HttpServletRequest req,Model m)
{
	hs=req.getSession();
	ArrayList<RouteBean> alr = aservr.viewAllRoutes();
	if(alr==null)
	{
		m.addAttribute("message", "No routes found");
		return new ModelAndView("RouteAdmin/SUCCESS");
	}
	hs.setAttribute("aaa", alr);
	return new ModelAndView("RouteAdmin/IdEntryModRoute");
}
@RequestMapping("/modByIdRoute")
public ModelAndView modByIdRoute(@RequestParam("routeid") String id,HttpSession hs1,HttpServletRequest req1, Model m)
{
	hs1=req1.getSession();
	ArrayList<RouteBean> alr = aservr.modByIdRoute(id);
	if(alr==null)
	{
		m.addAttribute("message", "No route found");
		return new ModelAndView("RouteAdmin/SUCCESS");
	}

	hs1.setAttribute("aab", alr);
	return new ModelAndView("RouteAdmin/ModifyRouteLanding");
}
@RequestMapping("/editRoute")
public ModelAndView editRoute(@RequestParam("routeid") String routeid,@RequestParam("source") String source,@RequestParam("destination") String destination,@RequestParam("fare") String fare,@RequestParam("travelduration") String travelduration,Model m)
{
	aservr.modRoute(routeid,source,destination,Integer.parseInt(fare),travelduration);
	//if(aservr.modRoute(rb))
	m.addAttribute("message", "Modify Route Successful");
	return new ModelAndView("RouteAdmin/SUCCESS");
	//else
	//	return new ModelAndView("RouteAdmin/IdEntryModRoute");
}


@RequestMapping("/schedule")
public ModelAndView scheduleLanding()
{
return new ModelAndView("ScheduleAdmin/ScheduleLanding");
}	
@RequestMapping("/addschedule")
public ModelAndView AddSchedule(Model m)
{
	ArrayList<ShipBean> sal = aserv.viewAllShips();
	ArrayList<RouteBean> ral = aservr.viewAllRoutes();
	m.addAttribute("ships", sal);
	m.addAttribute("routes", ral);
return new ModelAndView("ScheduleAdmin/AddSchedule");
}
@RequestMapping("/newSchedule")
public ModelAndView NewSchedule(@RequestParam("shipid") String shipid,@RequestParam("routeid") String routeid,@RequestParam("startdate") Date startdate,Model m)
{
	System.out.print(routeid);
String check = aservs.addSchedule(shipid,routeid,startdate);
if(check.equals("SUCCESS"))
{
	m.addAttribute("message", "Add Schedule Successful");
	return new ModelAndView("ScheduleAdmin/SUCCESS");
}
return new ModelAndView("Welcome");
}
@RequestMapping("/viewallschedule")
public ModelAndView ViewAllSchedule(HttpSession hs,HttpServletRequest req,Model m)
{
hs=req.getSession();
ArrayList<ScheduleBean> sb = aservs.viewAllSchedule();
if(sb.size()==0)
{
	m.addAttribute("message","No schedules found");
	return new ModelAndView("ScheduleAdmin/SUCCESS");
}
//System.out.println("Ship Id " + sb.get(0).getShip().getShipid());
hs.setAttribute("bbb", sb);
return new ModelAndView("ScheduleAdmin/ViewAll");
}
@RequestMapping("/viewscheduleid")
public ModelAndView ViewScheduleId()
{
return new ModelAndView("ScheduleAdmin/IdEntry");
}
@RequestMapping("/viewByIdSchedule")
public ModelAndView ViewScheduleById(@RequestParam("scheduleid") String id,HttpSession hs,HttpServletRequest req,Model m)
{
hs=req.getSession();
ArrayList<ScheduleBean> sb =aservs.viewScheduleById(id);
if(sb==null)
{
	m.addAttribute("message","No schedules found");
	return new ModelAndView("ScheduleAdmin/SUCCESS");
}
hs.setAttribute("bbb", sb);
return new ModelAndView("ScheduleAdmin/ViewById");
}
@RequestMapping("/modifyschedule")
public ModelAndView modifySchedule(HttpSession hs,HttpServletRequest req,Model m)
{
	hs=req.getSession();
	ArrayList<ScheduleBean> sb = aservs.viewAllSchedule();
	if(sb==null)
	{
		m.addAttribute("message","No schedules found");
		return new ModelAndView("ScheduleAdmin/SUCCESS");
	}
	//System.out.println("Ship Id " + sb.get(0).getShip().getShipid());
	hs.setAttribute("bbb", sb);
return new ModelAndView("ScheduleAdmin/IdEntryModSchedule");
}
@RequestMapping("/modByIdSchedule")
public ModelAndView modByIdSchedule(@RequestParam("scheduleid") String id,ModelMap m)
{

m.addAttribute("selectedschedule", aservs.modScheduleById(id));
return new ModelAndView("ScheduleAdmin/ModifyScheduleLanding");
}
@RequestMapping("/editSchedule")
public ModelAndView editSchedule(@RequestParam("shipid") String shipid,@RequestParam("scheduleid") String scheduleid,@RequestParam("routeid") String routeid,@RequestParam("startdate") Date startdate, Model m)
{
aservs.modSchedule(shipid,scheduleid,routeid,startdate);
m.addAttribute("message", "Modify Schedule Successful");
return new ModelAndView("ScheduleAdmin/SUCCESS");
}
@RequestMapping("/deleteSchedule")
public ModelAndView deleteSchedule(HttpSession hs,HttpServletRequest req,Model m)
{
	hs=req.getSession();
	ArrayList<ScheduleBean> sb = aservs.viewAllSchedule();
	if(sb==null)
	{
		m.addAttribute("message","No schedules found");
		return new ModelAndView("ScheduleAdmin/SUCCESS");
	}
	//System.out.println("Ship Id " + sb.get(0).getShip().getShipid());
	hs.setAttribute("bbb", sb);
return new ModelAndView("ScheduleAdmin/IdEntryDel");
}
@RequestMapping("/delByIdSchedule")
public ModelAndView delByIdSchedule(@RequestParam("scheduleid") String id,Model m)
{
if(aservs.removeSchedule(id)==1)
{
	m.addAttribute("message", "Delete Schedule Successful");
	return new ModelAndView("ScheduleAdmin/SUCCESS");
}
else
	return new ModelAndView("ScheduleAdmin/IdEntryDel");
}
@RequestMapping("/viewReservationDetails")
public ModelAndView viewReservationDetails(Model m)
{
	m.addAttribute("reservation",aserv.viewReservationDetails());
	return new ModelAndView("View Reservation Details");
}
}


