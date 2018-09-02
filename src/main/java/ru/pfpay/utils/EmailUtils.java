package ru.pfpay.utils;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

public class EmailUtils {


    public static boolean isValidEmail(String email) {

        EmailValidator emailValidator = new EmailValidator();

        return emailValidator.isValid(email, null);
    }
}
