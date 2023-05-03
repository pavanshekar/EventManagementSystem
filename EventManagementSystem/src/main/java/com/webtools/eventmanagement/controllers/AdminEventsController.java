package com.webtools.eventmanagement.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.webtools.eventmanagement.dao.EventDAO;
import com.webtools.eventmanagement.dao.RegistrationDAO;
import com.webtools.eventmanagement.pojo.Event;
import com.webtools.eventmanagement.pojo.User;
import com.webtools.eventmanagement.validator.EventValidator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminEventsController {
	
	@Autowired
    private EventValidator eventValidator;
	
	public AdminEventsController() {
    }
	
	@GetMapping("/edit-event")
    public ModelAndView showEditEventPage(@RequestParam int eventid, EventDAO eventdao, HttpServletRequest request, HttpServletResponse response) {
    	
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null || !((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("redirect:/login.htm");
		    return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView();
    	Event event = eventdao.getEvent(eventid);
    	modelAndView.addObject("event", event);
    	modelAndView.setViewName("edit-event");
        return modelAndView;
    }

    @GetMapping("/delete-user-event")
    public ModelAndView handleDeleteUserEvent(@RequestParam("id") int id, EventDAO eventdao, HttpServletRequest request, HttpServletResponse response) {
        
    	HttpSession session = request.getSession(false);

    	if (session == null || session.getAttribute("user") == null || !((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
    	    ModelAndView modelAndView = new ModelAndView();
    	    modelAndView.setViewName("redirect:/login.htm");
    	    return modelAndView;
    	}
    	
    	try {
    		eventdao.deleteEventById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Event> events = eventdao.getAllEvents();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("events", events);
        modelAndView.setViewName("admin-events");
        return modelAndView;
    }

    
    @PostMapping("/edit-event.htm")
    public ModelAndView updateEvent(@ModelAttribute("event") Event event, BindingResult result, @RequestParam("image") MultipartFile file, EventDAO eventdao, HttpServletRequest request, HttpServletResponse response) {

    	HttpSession session = request.getSession(false);

    	if (session == null || session.getAttribute("user") == null || !((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
    	    ModelAndView modelAndView = new ModelAndView();
    	    modelAndView.setViewName("redirect:/login.htm");
    	    return modelAndView;
    	}
    	
    	
    	ModelAndView modelAndView = new ModelAndView(); 

        // Validate event fields
        eventValidator.validate(event, result);
        
        List<ObjectError> errors = result.getAllErrors().stream()
                .filter(error -> !error.getCode().equals("typeMismatch") && !((FieldError) error).getField().equals("image"))
                .collect(Collectors.toList());

        if (!errors.isEmpty()) {
            modelAndView.addObject("event", event);
            modelAndView.addObject("errors", errors);
            modelAndView.setViewName("edit-event");
            return modelAndView;
        }

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                event.setImage(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        User host = (User) session.getAttribute("user");
        event.setHost(host);

        eventdao.updateEvent(event);
	    
	    List<Event> events = eventdao.getAllEvents();
	    modelAndView.addObject("events", events);
	    modelAndView.setViewName("admin-events");
	    return modelAndView;
    }

}
