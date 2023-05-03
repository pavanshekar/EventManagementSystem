package com.webtools.eventmanagement.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.webtools.eventmanagement.pojo.Event;

@Component
public class EventValidator implements Validator {

    private static final int MINIMUM_TICKETS = 1;

    public boolean supports(Class<?> clazz) {
        return Event.class.equals(clazz);
    }


    public void validate(Object target, Errors errors) {
    	
    	Event event = (Event) target;
    	
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required.event.name", "Event name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required.event.description", "Event description is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "field.required.event.location", "Event location is required");
        ValidationUtils.rejectIfEmpty(errors, "startDateTime", "field.required.event.startDateTime", "Start date and time are required");
        ValidationUtils.rejectIfEmpty(errors, "endDateTime", "field.required.event.endDateTime", "End date and time are required");
        ValidationUtils.rejectIfEmpty(errors, "numberOfTickets", "field.required.event.numberOfTickets", "Number of tickets is required");
        ValidationUtils.rejectIfEmpty(errors, "cost", "field.required.event.cost", "Event cost is required");
        
        if (event.getNumberOfTickets() < MINIMUM_TICKETS) {
            errors.rejectValue("numberOfTickets", "field.min.value",
                    new Object[]{MINIMUM_TICKETS},
                    "The number of tickets must be at least " + MINIMUM_TICKETS);
        }

        if (event.getStartDateTime() != null && event.getEndDateTime() != null) {
            if (event.getStartDateTime().isAfter(event.getEndDateTime())) {
                errors.rejectValue("startDateTime", "field.invalid.event.startDateTime", "Start date and time must be before end date and time");
            }
        }
         
    }
}
