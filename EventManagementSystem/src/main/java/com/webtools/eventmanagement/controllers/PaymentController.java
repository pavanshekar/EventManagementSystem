package com.webtools.eventmanagement.controllers;



import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.webtools.eventmanagement.dao.EventDAO;
import com.webtools.eventmanagement.dao.PaymentDAO;
import com.webtools.eventmanagement.dao.RegistrationDAO;
import com.webtools.eventmanagement.pojo.Payment;
import com.webtools.eventmanagement.pojo.User;
import com.webtools.eventmanagement.util.HibernateUtil;
import com.webtools.eventmanagement.pojo.Event;
import com.webtools.eventmanagement.pojo.Registration;
import com.webtools.eventmanagement.validator.PaymentValidator;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import java.util.Properties;


@Controller
public class PaymentController {
	
	@Autowired
    private PaymentValidator paymentValidator;;
	
	@GetMapping("/payment.htm")
	public String showPaymentsPage(Model model, HttpServletRequest request, HttpServletResponse response) {
	    
		HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null || ((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
            return "redirect:/login.htm";
        }
		
		Payment payment = new Payment();
	    model.addAttribute("payment", payment);
	    return "payment";
	}
	
	@PostMapping("/payment.htm")
    public String processPayment(@ModelAttribute("payment") Payment payment, BindingResult result, PaymentDAO paymentDao, EventDAO eventDao, RegistrationDAO registrationDao, HttpServletRequest request, HttpServletResponse response) {

		
		HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null || ((User) session.getAttribute("user")).getEmail().equals("admin@gmail.com")) {
            return "redirect:/login.htm";
        }
        
        paymentValidator.validate(payment, result);

        // Check for validation errors
        if (result.hasErrors()) {
            return "payment";
        }
        

        User user = (User) session.getAttribute("user");
	    
	    int numberOfTickets = (int) session.getAttribute("numberOfTickets");
	    
	    Event event = (Event) session.getAttribute("event");
	    
	    
	 // Check if the user has already registered for this event
	    Registration existingRegistration = registrationDao.getRegistrationByUserAndEvent(user, event);
	    
	    if (existingRegistration != null) {
	        // If the user has already registered for this event, update the number of tickets
	        existingRegistration.setNumberOfTickets(existingRegistration.getNumberOfTickets() + numberOfTickets);
	        existingRegistration.setTotalPrice((existingRegistration.getNumberOfTickets() + numberOfTickets)*event.getCost());
	        registrationDao.updateRegistration(existingRegistration);
	        
	        // Update the number of tickets for the event in the database
	        event.setNumberOfTickets(event.getNumberOfTickets() - numberOfTickets);
	        eventDao.mergeEvent(event);
	        
	        payment.setRegistration(existingRegistration);
	        
	    } else {
	        // If the user has not already registered for this event, create a new registration object
	        double totalPrice = event.getCost() * numberOfTickets;
	    
	        Registration registration = new Registration();
	        registration.setUser(user);
	        registration.setEvent(event);
	        registration.setNumberOfTickets(numberOfTickets);
	        registration.setTotalPrice(totalPrice);
	    
	        registrationDao.saveRegistration(registration);
	        
	        // Update the number of tickets for the event in the database
	        event.setNumberOfTickets(event.getNumberOfTickets() - numberOfTickets);
	        eventDao.mergeEvent(event);
	        
	        payment.setRegistration(registration);
	     
	    }
	    
	    paymentDao.savePayment(payment);
	    
	 // Construct the email message
	    String recipient = user.getEmail();
	    String subject = "Event Registration Confirmation";
	    String messageBody = "Dear " + user.getFirstName() + ",<br><br>"
	            + "Thank you for registering for the " + event.getName() + " event. Your registration has been confirmed.<br><br>"
	            + "Event details:<br><br>"
	            + "Description: " + event.getDescription() + "<br><br>"
	            + "Start: " + event.getStartDateTime() + "<br><br>"
	            + "End: " + event.getEndDateTime() + "<br><br>"
	            + "Location: " + event.getLocation() + "<br><br>"
	            + "Number of tickets: " + numberOfTickets + "<br><br>"
	            + "Total price: " + payment.getTotalPrice() + "<br><br>"
	            + "We look forward to seeing you at the event.<br><br>"
	            + "Best regards,<br>"
	            + "The Event Team";
	    		
	    // Send the email
	    try {
	        sendEmail(recipient, subject, messageBody, true);
	        System.out.println("Email sent successfully");
	    } catch (MessagingException e) {
	        System.out.println("Failed to send email");
	        e.printStackTrace();
	    }


	    
	    session.removeAttribute("numberOfTickets");
	    session.removeAttribute("event");
	    
	    return "redirect:/my-events.htm";
	    
	    
	}
	
	
	public void sendEmail(String recipient, String subject, String messageBody, boolean isHtml) throws MessagingException {
	    // Set up the SMTP server properties
	    Properties properties = new Properties();
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", "587");

	    // Create a new session with an authenticator
	    Session session = Session.getInstance(properties, new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication("no.reply.eventmanagementsystem@gmail.com", "owvfepqvjoealnwx");
	        }
	    });

	    // Create a new message
	    Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress("no.reply.eventmanagementsystem@gmail.com"));
	    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
	    message.setSubject(subject);

	    // Set the message body and format
	    if (isHtml) {
	        message.setContent(messageBody.replaceAll("\n", "<br>"), "text/html; charset=utf-8");
	    } else {
	        message.setText(messageBody);
	    }

	    // Send the message
	    Transport.send(message);
	}


	    
}
