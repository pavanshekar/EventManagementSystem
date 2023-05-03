package com.webtools.eventmanagement.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webtools.eventmanagement.dao.EventDAO;
import com.webtools.eventmanagement.dao.RegistrationDAO;
import com.webtools.eventmanagement.dao.UserDAO;
import com.webtools.eventmanagement.pojo.Event;
import com.webtools.eventmanagement.pojo.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	
	public AdminController() {
    }
	
	@GetMapping("/admin.htm")
    public ModelAndView showAdminPage(UserDAO userdao, EventDAO eventdao,  RegistrationDAO registrationdao, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null || !((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("redirect:/login.htm");
		    return modelAndView;
		}

		
		int totalUsers = userdao.getCountAllUsers();
		int totalEvents = eventdao.getCountAllEvents();
	    Double totalRevenue = registrationdao.getTotalRevenue();
	    
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("totalUsers", totalUsers);
	    modelAndView.addObject("totalEvents", totalEvents);
	    modelAndView.addObject("totalRevenue", totalRevenue);
	    modelAndView.setViewName("admin");
	    return modelAndView;
    }
	
	@GetMapping("/admin-events.htm")
    public ModelAndView showAdminEventsPage(EventDAO eventdao, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null || !((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("redirect:/login.htm");
		    return modelAndView;
		}
		
		List<Event> events = eventdao.getAllEvents();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("events", events);
        modelAndView.setViewName("admin-events");
        return modelAndView;
    }
	
	@GetMapping("/admin-users.htm")
    public ModelAndView showAdminUsersPage(UserDAO userdao, HttpServletRequest request, HttpServletResponse response) {
        
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null || !((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("redirect:/login.htm");
		    return modelAndView;
		}
		
		List<User> users = userdao.getAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("admin-users");
        return modelAndView;
    }


}
