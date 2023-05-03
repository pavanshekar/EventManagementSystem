package com.webtools.eventmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webtools.eventmanagement.dao.AddressDAO;
import com.webtools.eventmanagement.dao.UserDAO;
import com.webtools.eventmanagement.pojo.Address;
import com.webtools.eventmanagement.pojo.User;
import com.webtools.eventmanagement.validator.RegisterValidator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class RegisterController {
	
	public RegisterController() {
    }
	
	@Autowired
	private RegisterValidator registerValidator;
	
	
	@GetMapping("/register.htm")
	public String showRegistrationForm(Model model) {
	    User user = new User();
	    Address address = new Address();
	    user.setAddress(address);
	    model.addAttribute("user", user);
	    return "register";
	}
	
	
	@PostMapping("/register.htm")
	public ModelAndView handleRegister(@ModelAttribute("user") User user, BindingResult result, UserDAO userdao, AddressDAO addressdao) {
		registerValidator.validate(user, result);
	    if (result.hasErrors()) {
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("register");
	        return modelAndView;
	    }
	    
	 // Check if user already exists with the entered email
	    String email = user.getEmail();
	    User existingUser = userdao.getUserByEmail(email);
	    if (existingUser != null) {
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.addObject("user", user);
	        modelAndView.addObject("error", "User with email " + email + " already exists");
	        modelAndView.setViewName("register");
	        return modelAndView;
	    }

	    Address address = user.getAddress();
	    addressdao.saveAddress(address);
	    userdao.saveUser(user);

	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("user", new User());
	    modelAndView.addObject("message", "Registration successful. Please log in.");
	    modelAndView.setViewName("login");
	    return modelAndView;

	}
	
	@GetMapping("/update-profile.htm")
    public String showUpdateProfilePage(Model model, UserDAO userdao, HttpServletRequest request, HttpServletResponse response) {
    	
		HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null || ((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
            return "redirect:/login.htm";
        }
		
    	User user = (User) session.getAttribute("user");
    	Address address = user.getAddress();
        model.addAttribute("user", user);
        model.addAttribute("address", address);
        return "update-profile";
    }
    
	
	@PostMapping("/update-profile.htm")
	public ModelAndView handleUpdateProfile(@ModelAttribute("user") User user, BindingResult result, UserDAO userdao, AddressDAO addressdao, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null || ((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("redirect:/login.htm");
		    return modelAndView;
		}
		
		registerValidator.validate(user, result);
	    if (result.hasErrors()) {
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("update-profile");
	        return modelAndView;
	    }
	    
	 // Check if user already exists with the entered email
	    String email = user.getEmail();
	    User existingUser = userdao.getUserByEmail(email);
	    if (existingUser != null) {
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.addObject("user", user);
	        modelAndView.addObject("error", "User with email " + email + " already exists");
	        modelAndView.setViewName("update-profile");
	        return modelAndView;
	    }

	    Address address = user.getAddress();
	    addressdao.updateAddress(address);
	    
	    userdao.updateUser(user);


      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("user", user);
      modelAndView.addObject("address", address);
      modelAndView.setViewName("update-profile");
      return modelAndView;
	
	}
		


}
