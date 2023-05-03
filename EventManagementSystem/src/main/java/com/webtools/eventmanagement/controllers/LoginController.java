package com.webtools.eventmanagement.controllers;

import com.webtools.eventmanagement.dao.AddressDAO;
import com.webtools.eventmanagement.dao.EventDAO;
import com.webtools.eventmanagement.dao.RegistrationDAO;
import com.webtools.eventmanagement.dao.UserDAO;
import com.webtools.eventmanagement.pojo.Event;
import com.webtools.eventmanagement.pojo.User;
import com.webtools.eventmanagement.validator.LoginValidator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
	
	@Autowired
    private LoginValidator loginValidator;

    public LoginController() {
    }

    @GetMapping("/index.htm")
    public String showHomePage() {
        return "index";
    }
    
    @GetMapping("/login.htm")
	public String showLoginPage(Model model) {
	    User user = new User();
	    model.addAttribute("user", user);
	    return "login";
	}

    @PostMapping("/login.htm")
    public ModelAndView handleLogin(@ModelAttribute("user") User user, BindingResult result, UserDAO userdao, AddressDAO addressdao, EventDAO eventdao, RegistrationDAO registrationdao, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        
        // Validate user input using the LoginValidator
        loginValidator.validate(user, result);

        // Check for validation errors
        if (result.hasErrors()) {
            modelAndView.setViewName("login");
            return modelAndView;
        }

        // Retrieve the user by email and password
        String email = user.getEmail();
        String password = user.getPassword();
        User retrievedUser = userdao.getUserByEmailAndPassword(email, password);

        // Check if user was found
        if (retrievedUser != null) {
        	
        	// Add the user to the model and session
            modelAndView.addObject("user", retrievedUser);
            HttpSession session = request.getSession(false);
            session.setAttribute("user", retrievedUser);
        	
        	if (email.equals("admin@gmail.com")) {
        		
        		int totalUsers = userdao.getCountAllUsers();
        		int totalEvents = eventdao.getCountAllEvents();
        	    Double totalRevenue = registrationdao.getTotalRevenue();

        	    modelAndView.addObject("totalUsers", totalUsers);
        	    modelAndView.addObject("totalEvents", totalEvents);
        	    modelAndView.addObject("totalRevenue", totalRevenue);
        	    modelAndView.setViewName("admin");
        	    
        	    return modelAndView;
            }

            // Retrieve all events and add to the model
            List<Event> events = eventdao.getAllEvents();
            modelAndView.addObject("events", events);

            // Redirect to the events page
            modelAndView.setViewName("events");
        } else {
            // Set error message and redirect back to login page
            modelAndView.addObject("error", "Invalid email or password");
            modelAndView.setViewName("login");
        }

        return modelAndView;
    }

    @GetMapping("/signout.htm")
    public ModelAndView handleLogout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "You have been successfully logged out.");
        modelAndView.addObject("user", new User()); 
        modelAndView.setViewName("login");
        return modelAndView;
    }


}
