package com.danskeit.srs2.controller;

import java.io.IOException;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.danskeit.srs2.bean.*;
import com.danskeit.srs2.util.ReservationPDFExport;
import com.danskeit.srs2.util.User;
import com.lowagie.text.DocumentException;
import com.danskeit.srs2.service.*;

@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	private CustomerService cserv;
	@Autowired
	private User auth;
	@Autowired
	private AdminServiceSchedule aservs;
	
	
	@RequestMapping("/changePassCustomer")
	public ModelAndView changePassLanding(ModelMap m)
	{
		//System.out.println(session.getAttribute("user"));
		return new ModelAndView("User/ChangePassLandingCustomer");
	}
	@RequestMapping("/changePassSubmitCustomer")
	public ModelAndView changePassword(@RequestParam("newPass") String newPass,HttpSession hs,Model m)
	{
		String id=(String) hs.getAttribute("user");
		if(auth.changePassword(id,newPass).equals("SUCCESS"))
		{
			m.addAttribute("message", "Change Password Successful");
			return new ModelAndView("User/SUCCESS");
		}
		else
		{
			{
				m.addAttribute("message", "Change Password Unsuccessful");
				return new ModelAndView("User/SUCCESS");
			}
		}
	}
	
	
	@RequestMapping("/viewSchedule")
	public ModelAndView viewSchedule(Model m)
	{
		ArrayList<ScheduleBean> sb = aservs.viewAllSchedule();
		m.addAttribute("schedules",sb);
		
		return new ModelAndView("User/ViewSchedule");
	}
	@RequestMapping("/viewScheduleDetails")
	public ModelAndView viewScheduleDetails(@RequestParam("source") String source,@RequestParam("destination") String destination,@RequestParam("date") Date date,ModelMap m)
	{
		System.out.println(date);
		ArrayList<ScheduleBean> al = cserv.viewScheduleByRoute(source, destination, date);
		if(al.size()==0)
		{
			m.addAttribute("message", "No schedule for these details");
			return new ModelAndView("User/SUCCESS");
		}
		m.addAttribute("schedules",al);
		
		return new ModelAndView("User/ViewScheduleDetails");
	}
	@RequestMapping("/reservation")
	public ModelAndView reservationLanding(Model m)
	{
		
		return new ModelAndView("User/AddReservation");
	}
	@RequestMapping("/reservation1")
	public ModelAndView reservationLanding1(@RequestParam("scheduleid") String scheduleid, @RequestParam("journeydate") Date journeydate,Model m)
	{
		m.addAttribute("scheduleid",scheduleid);
		m.addAttribute("journeydate", journeydate);
		
		return new ModelAndView("User/AddReservation");
	}
	@RequestMapping("/newReservation")
	public ModelAndView newReservation(@RequestParam("scheduleid") String scheduleid,ReservationBean rb,Model m,HttpSession hs)
	{
		String available;
		double amt = cserv.getTotalFare(scheduleid,rb.getNoofseats());
		if(amt==0)
		{
			m.addAttribute("message", "Insufficient number of seats available");
			return new ModelAndView("User/SUCCESS");
		}
		System.out.println(amt);

		//System.out.println(rb.getCredentials().getUserid());
		hs.setAttribute("reservation_totalamt",amt);
		hs.setAttribute("noofseats",rb.getNoofseats());
		hs.setAttribute("scheduleid", scheduleid);
		hs.setAttribute("reservation",rb);
		
		//return new ModelAndView("User/ConfirmReservation");
		ModelAndView model = new ModelAndView();
	    PassengerList wrapper1 = new PassengerList();
	    ArrayList<PassengerBean> wrapper = new ArrayList<PassengerBean>();
	    for(int i=0;i<rb.getNoofseats();i++)
	    {
	    	PassengerBean pb=new PassengerBean();
	    	wrapper.add(pb);
	    }
	    wrapper1.setPassengers(wrapper);
	    System.out.print(wrapper.size());
	    model.addObject("passenger",wrapper1);
	    model.setViewName("User/ConfirmReservation");
	    return model;
	}
	
	@RequestMapping(value = "/newPassenger", method = RequestMethod.POST)
	public ModelAndView newPassengerDetails(@ModelAttribute("passenger") PassengerList passenger,@RequestParam("creditcardnumber") String creditcardnumber,HttpSession hs,Model m)
	{
		System.out.print("hello");
	    System.out.println(passenger.getPassengers());
		System.out.print(creditcardnumber);
		ReservationBean rb = (ReservationBean) hs.getAttribute("reservation");
		
		List<PassengerBean> pblist = passenger.getPassengers();
		System.out.println(pblist.get(0).getGender());
		rb.setTotalfare((double)hs.getAttribute("reservation_totalamt"));
		
		String id=(String) hs.getAttribute("user");
		String scheduleid=(String) hs.getAttribute("scheduleid");
		String status = cserv.makeReservation(rb,pblist,creditcardnumber,id,scheduleid);
		if(status.equals("SUCCESS"))
		{
			m.addAttribute("message", "Reservation Successful");
			return new ModelAndView("User/SUCCESS");
		}
		else
		{
			m.addAttribute("message", status);
			return new ModelAndView("User/SUCCESS");
		}
	}
	@RequestMapping("viewReservation")
	public ModelAndView viewReservation()
	{
		return new ModelAndView("User/IdEntryView");
	}
	@RequestMapping("viewByIdReservation")
	public ModelAndView viewReservationById(@RequestParam("reservationid") String id,ModelMap m,HttpSession hs)
	{
		Map<ReservationBean,List<PassengerBean>> mp = cserv.viewReservationById(id);
		if(mp.isEmpty())
		{
			m.addAttribute("message", "No Reservation for this id");
			return new ModelAndView("User/SUCCESS");
		}
		hs.setAttribute("selectedReservation", mp );
		return new ModelAndView("User/ViewReservation");
	}
	
	@RequestMapping("/viewAllReservation")
	public ModelAndView viewAllReservation(ModelMap m,HttpSession hs)
	{
		ArrayList<ReservationBean> rb = cserv.viewAllReservation();
		if(rb==null)
		{
			m.addAttribute("message", "No Reservation ");
			return new ModelAndView("User/SUCCESS");
		}
		hs.setAttribute("Reservations", rb );
		return new ModelAndView("User/ViewAllReservation");
	}
	
	@RequestMapping("/deleteReservation")
	public ModelAndView meth12(Model m,HttpSession hs)
	{
		ArrayList<ReservationBean> rb = cserv.viewAllReservation();
		if(rb==null)
		{
			m.addAttribute("message", "No Reservation ");
			return new ModelAndView("User/SUCCESS");
		}
		hs.setAttribute("Reservations", rb );
		return new ModelAndView("User/IdEntryDel");
	}
	
	@RequestMapping("/printTicket")
	public ModelAndView printTicket(Model m,HttpSession hs)
	{
		ArrayList<ReservationBean> rb = cserv.viewAllReservation();
		if(rb==null)
		{
			m.addAttribute("message", "No Reservation ");
			return new ModelAndView("User/SUCCESS");
		}
		hs.setAttribute("Reservations", rb );
		return new ModelAndView("User/PrintReservation");
	}
	@RequestMapping("/delByIdReservation")
	public ModelAndView delByIdReservation(@RequestParam("reservationid") String id,Model m)
	{
		if(cserv.removeReservation(id)==1)
		{
			m.addAttribute("message", "Delete Reservation Successful");
			return new ModelAndView("User/SUCCESS");
		}
		else
			return new ModelAndView("User/IdEntryDel");
	}
	
	
	 @RequestMapping("/export/pdf")
	    public void exportToPDF(@RequestParam("reservationid") String reservationid,HttpServletResponse response,HttpSession hs,Model m) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	        String currentDateTime = dateFormatter.format(new Date(0));
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=Reservation_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	        
	        
	        Map<ReservationBean,List<PassengerBean>> mp = cserv.viewReservationById(reservationid);
			if(mp.isEmpty())
			{
				m.addAttribute("message", "No Reservation for this id");
				//return new ModelAndView("User/SUCCESS");
			}
			hs.setAttribute("selectedReservation", mp );
	        //Map<ReservationBean,List<PassengerBean>> al=(Map<ReservationBean,List<PassengerBean>>)hs.getAttribute("selectedReservation"); 


	        
	        ReservationBean rb=new ReservationBean();
	        List<PassengerBean> pb = null;
	        for (Map.Entry<ReservationBean,List<PassengerBean>> me : mp.entrySet()) {
	                rb=me.getKey();
	        		pb=me.getValue();
	            }
	      

	       
	         
	        ReservationPDFExport exporter = new ReservationPDFExport(pb,rb);
	        exporter.export(response);
	         
	    }
	 
	 
	 
	
}
