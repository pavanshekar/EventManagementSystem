package com.webtools.eventmanagement.controllers;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webtools.eventmanagement.dao.EventDAO;
import com.webtools.eventmanagement.dao.RegistrationDAO;
import com.webtools.eventmanagement.pojo.Event;
import com.webtools.eventmanagement.pojo.Registration;
import com.webtools.eventmanagement.pojo.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class EventRegistrationController {

	@GetMapping("/register-event")
    public ModelAndView showRegisterEventPage(@RequestParam("eventId") int eventId, EventDAO eventdao, HttpServletRequest request, HttpServletResponse response) {
        
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null || ((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("redirect:/login.htm");
		    return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView();
        Event event = eventdao.getEvent(eventId);
        modelAndView.addObject("event", event);
        modelAndView.setViewName("register-event");
        return modelAndView;
    }
	
	
	@PostMapping("/register-event.htm")
	public String registerEvent(EventDAO eventdao, RegistrationDAO registrationdao, HttpServletRequest request, HttpServletResponse response) {
	    	
		HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null || ((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
            return "redirect:/login.htm";
        }
		
		int eventId = Integer.parseInt(request.getParameter("eventId"));
	    int numberOfTickets = Integer.parseInt(request.getParameter("numberOfTickets"));
	    
//	    EventDAO eventDao = new EventDAO();
//	    Event event = eventDao.getEvent(eventId);
	    
	    Event event = eventdao.getEvent(eventId);
	    
	    double totalPrice = event.getCost() * numberOfTickets;
	    
	    session.setAttribute("event", event);
	    session.setAttribute("numberOfTickets", numberOfTickets);
	    session.setAttribute("totalPrice", totalPrice);
	    
	    return "redirect:/payment.htm";
	    
	}

	
	@GetMapping("/my-events.htm")
	public ModelAndView showMyEventsPage(EventDAO eventdao, RegistrationDAO registrationDao, HttpServletRequest request, HttpServletResponse response) {
	    
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null || ((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("redirect:/login.htm");
		    return modelAndView;
		}
		
	    User user = (User) session.getAttribute("user");

	    Set<Registration> registrations = registrationDao.getRegistrationsByUser(user);
	    List<Event> events = new ArrayList<>();
	    List<Integer> tickets = new ArrayList<>();

	    for (Registration registration : registrations) {
	    	int numberOfTickets = registration.getNumberOfTickets();
	        Event event = eventdao.getEvent(registration.getEvent().getEventid());
	        byte[] image = event.getImage();
	        if (image != null && image.length > 0) {
	            event.setBase64Image(Base64.getEncoder().encodeToString(image));
	        }
	        tickets.add(numberOfTickets);
	        events.add(event);
	    }
	    
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("events", events);
	    modelAndView.addObject("tickets", tickets);
	    modelAndView.setViewName("my-events");
	    return modelAndView;
	}



	
}
