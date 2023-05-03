package com.webtools.eventmanagement.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.webtools.eventmanagement.pojo.User;

@Component
public class LoginValidator implements Validator {

    private static final int MINIMUM_PASSWORD_LENGTH = 6;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$");

    public boolean supports(Class clazz) {
       return User.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required.user.email", "Email is required");
    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required.user.password", "Password is required");

       User login = (User) target;
       String password = login.getPassword();
       
       if (password != null && password.trim().length() < MINIMUM_PASSWORD_LENGTH) {
          errors.rejectValue("password", "field.min.length",
                new Object[]{MINIMUM_PASSWORD_LENGTH},
                "The password must be at least [" + MINIMUM_PASSWORD_LENGTH + "] characters in length.");
       }
       
       Matcher matcher = PASSWORD_PATTERN.matcher(password);
       if (password != null && !matcher.matches()) {
           errors.rejectValue("password", "field.pattern.invalid",
                   "The password must contain at least one lowercase, one uppercase, one number, and one special character.");
       }
    }
    
}
