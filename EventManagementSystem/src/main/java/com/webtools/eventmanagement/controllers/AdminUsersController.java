package com.webtools.eventmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class AdminUsersController {
	
	@Autowired
	private RegisterValidator registerValidator;
	
	public AdminUsersController() {
    }
	
	@GetMapping("/edit-user")
	public ModelAndView showEditUserPage(@RequestParam int userid, UserDAO userdao, HttpServletRequest request, HttpServletResponse response) {
	    
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null || !((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("redirect:/login.htm");
		    return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView();
	    User user = userdao.getUser(userid);
	    Address address = user.getAddress();
	    modelAndView.addObject("user", user);
	    modelAndView.addObject("address", address);
	    modelAndView.setViewName("edit-user");
	    return modelAndView;
	}
	
	@PostMapping("/edit-user.htm")
	public ModelAndView handleUpdateProfile(@ModelAttribute("user") User user, BindingResult result, UserDAO userdao, AddressDAO addressdao, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null || !((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("redirect:/login.htm");
		    return modelAndView;
		}
		
		
		registerValidator.validate(user, result);
	    if (result.hasErrors()) {
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("edit-user");
	        return modelAndView;
	    }

	    Address address = user.getAddress();
	    addressdao.updateAddress(address);
	    
	    userdao.updateUser(user);


      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("user", user);
      modelAndView.addObject("address", address);
      modelAndView.setViewName("edit-user");
      return modelAndView;
	
	}
	
	@GetMapping("/delete-user")
	public ModelAndView deleteUser(@RequestParam int userid, UserDAO userdao, HttpServletRequest request, HttpServletResponse response) {
	    
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null || !((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("redirect:/login.htm");
		    return modelAndView;
		}
		
		userdao.deleteUserById(userid);
	    List<User> users = userdao.getAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("admin-users");
        return modelAndView;
	}
	
	@GetMapping("/update-admin-profile.htm")
	public ModelAndView showUpdateAdminProfilePage(UserDAO userdao, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null || !((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("redirect:/login.htm");
		    return modelAndView;
		}
		
		User user = (User) session.getAttribute("user");
		
		ModelAndView modelAndView = new ModelAndView();
    	Address address = user.getAddress();
    	modelAndView.addObject("user", user);
    	modelAndView.addObject("address", address);
    	modelAndView.setViewName("update-admin-profile");
        return modelAndView;
	}
	
	@PostMapping("/update-admin-profile.htm")
	public ModelAndView handleUpdateAdminProfile(@ModelAttribute("user") User user, BindingResult result, UserDAO userdao, AddressDAO addressdao, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null || !((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("redirect:/login.htm");
		    return modelAndView;
		}
		
		registerValidator.validate(user, result);
	    if (result.hasErrors()) {
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("update-admin-profile");
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
