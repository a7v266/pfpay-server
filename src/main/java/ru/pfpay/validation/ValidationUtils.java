package ru.pfpay.validation;

import ru.pfpay.domain.ErrorCollector;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtils {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public static ErrorCollector validate(Object object) {
        return validate(object, new ErrorCollector());
    }

    public static ErrorCollector validate(Object object, ErrorCollector errorCollector) {
        for (ConstraintViolation item : VALIDATOR.validate(object)) {
            errorCollector.add(item.getMessage());
        }
        return errorCollector;
    }

    public static void addConstraintViolation(ConstraintValidatorContext context, ErrorCollector errorCollector) {
        context.disableDefaultConstraintViolation();
        errorCollector.getErrors().forEach(
                errorMessage -> context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation());
    }
}
