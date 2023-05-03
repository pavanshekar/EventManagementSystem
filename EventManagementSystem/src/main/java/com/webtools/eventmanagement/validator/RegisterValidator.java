package com.webtools.eventmanagement.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.webtools.eventmanagement.pojo.Address;
import com.webtools.eventmanagement.pojo.User;

@Component
public class RegisterValidator implements Validator {
    
    private static final int MINIMUM_PASSWORD_LENGTH = 6;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\d{10}");
    
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz) || Address.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (target instanceof User) {
            User user = (User) target;
            String password = user.getPassword();
            String phone = user.getPhone();

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required.user.firstName", "First name is required.");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.required.user.lastName", "Last name is required.");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required.user.email", "Email is required.");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required.user.password", "Password is required.");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "field.required.user.phone", "Phone number is required.");

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

            Matcher phoneMatcher = PHONE_PATTERN.matcher(phone);
            if (phone != null && !phoneMatcher.matches()) {
                errors.rejectValue("phone", "field.pattern.invalid",
                        "Phone number must be exactly 10 digits.");
            }
        } else if (target instanceof Address) {
            Address address = (Address) target;

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "streetNum", "field.required.address.streetNum", "Street number is required.");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "streetName", "field.required.address.streetName", "Street name is required.");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "field.required.address.city", "City is required.");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "field.required.address.state", "State is required.");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "field.required.address.zip", "Zip code is required.");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "field.required.address.country", "Country is required.");
        }
    }

}
