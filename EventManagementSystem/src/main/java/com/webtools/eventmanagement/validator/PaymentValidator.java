package com.webtools.eventmanagement.validator;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.webtools.eventmanagement.pojo.Payment;

@Component
public class PaymentValidator implements Validator {

    private static final String CARD_NUMBER_PATTERN = "^\\d{16}$";
    private static final int CVV_LENGTH = 3;
    private static final DateTimeFormatter EXPIRY_DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/yy");

    public boolean supports(Class<?> clazz) {
        return Payment.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        Payment payment = (Payment) target;
        String cardNumber = payment.getCardNumber();
        int cvv = payment.getCvv();
        String expiryDate = payment.getExpiryDate();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardHolderName", "field.required.payment.cardHolderName",
                "Cardholder Name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardNumber", "field.required.payment.cardNumber",
                "Card Number is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expiryDate", "field.required.payment.expiryDate",
                "Expiry Date is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cvv", "field.required.payment.cvv", "CVV is required");

        if (cardNumber != null && !cardNumber.matches(CARD_NUMBER_PATTERN)) {
            errors.rejectValue("cardNumber", "field.pattern.invalid", "Invalid Card Number");
        }

        if (cvv <= 0) {
            errors.rejectValue("cvv", "field.pattern.invalid", "Invalid CVV");
        } else if (String.valueOf(cvv).length() != CVV_LENGTH) {
            errors.rejectValue("cvv", "field.invalid", "CVV must be " + CVV_LENGTH + " digits long");
        }

        if (expiryDate != null) {
            try {
                YearMonth expiryYearMonth = YearMonth.parse(expiryDate, EXPIRY_DATE_FORMATTER);
                if (expiryYearMonth.isBefore(YearMonth.now())) {
                    errors.rejectValue("expiryDate", "field.expiryDate.invalid", "Card has expired");
                }
            } catch (DateTimeParseException e) {
                errors.rejectValue("expiryDate", "field.expiryDate.invalid", "Invalid Expiry Date");
            }
        }
    }

}
