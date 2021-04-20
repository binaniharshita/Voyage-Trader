package com.danskeit.srs2.controller;

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

import com.danskeit.srs2.bean.CredentialsBean;
import com.danskeit.srs2.bean.CreditCardBean;
import com.danskeit.srs2.bean.ProfileBean;
import com.danskeit.srs2.util.User;

import com.danskeit.srs2.service.*;
@RestController
public class LoginController {
	@Autowired
	private User auth;
	@Autowired
	private CustomerService cserv;

@RequestMapping("/adminhome")
public ModelAndView adminhome()
{
	return new ModelAndView("WelcomeAdmin");
}
@RequestMapping("/customerhome")
public ModelAndView customerhome()
{
	return new ModelAndView("WelcomeEmployee");
}
@RequestMapping("/addCard")
public ModelAndView viewCard(Model m,HttpSession hs)
{
	String id=(String) hs.getAttribute("user");
	CreditCardBean ccb = cserv.getCardDetails(id);
	
	m.addAttribute("cardDetails", ccb);
	return new ModelAndView("User/CardDetails");
}
@RequestMapping("/newCard")
public ModelAndView newCard(CreditCardBean ccb,Model m)
{
	String check = cserv.updateCardDetails(ccb);
	m.addAttribute("message", check);
	return new ModelAndView("User/SUCCESS");
	//return new ModelAndView("WelcomeEmployee");
}
	
	
	@RequestMapping("/changePass")
	public ModelAndView changePassLanding(ModelMap m)
	{
		//System.out.println(session.getAttribute("user"));
		return new ModelAndView("ChangePassLanding");
	}
	@RequestMapping("/changePassSubmit")
	public ModelAndView changePassword(@RequestParam("newPass") String newPass,HttpSession hs,Model m)
	{
		String id=(String) hs.getAttribute("user");
		if(auth.changePassword(id,newPass).equals("SUCCESS"))
		{
			m.addAttribute("message", "Change Password Successful");
			return new ModelAndView("SUCCESS");
		}
		else
		{
			{
				m.addAttribute("message", "Change Password Unsuccessful");
				return new ModelAndView("SUCCESS");
			}
		}
	}
	@RequestMapping("/welcome")
	public ModelAndView welcomeLanding()
	{
		return new ModelAndView("Welcome");
	}
	@RequestMapping("/login")
	public ModelAndView loginCheck(@ModelAttribute("cb") CredentialsBean cb,Model m,HttpSession hs,HttpServletRequest req)
	{
		hs=req.getSession();
		hs.setAttribute("user", cb.getUserid());
		
		String check = auth.login(cb);
		if(check.equals("C"))
			return new ModelAndView("WelcomeEmployee");
		else if(check.equals("A"))
			return new ModelAndView("WelcomeAdmin");
		else
			return new ModelAndView("Welcome");
	}

	@RequestMapping("/register")
	public ModelAndView register()
	{
		
		return new ModelAndView("Register");
	}
	@RequestMapping("/newRegister")
	public ModelAndView newUser(@ModelAttribute("pb")ProfileBean pb,@ModelAttribute("cb") CredentialsBean cb,Model m)
	{
		System.out.println("Hello" + pb.getFirstname());
		String email = pb.getEmailid();
		String check = auth.register(pb,cb,pb.getFirstname());
		//m.addAttribute("id",check);
		if(check.equals("Registered"))
		{
			m.addAttribute("id",auth.returnId(email));
			return new ModelAndView("RegistrationSuccess");
		}
		return new ModelAndView("RegistrationSuccess");
	}
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession hs)
	{
		String id=(String) hs.getAttribute("user");
		if(auth.logout(id))
			return new ModelAndView("Welcome");
		else
			return new ModelAndView("WelcomeAdmin");
	}
}
