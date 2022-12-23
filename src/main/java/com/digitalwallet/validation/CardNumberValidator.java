package com.digitalwallet.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CardNumberValidator implements ConstraintValidator<CardNumber, String> {
    @Override
    public void initialize(CardNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return true; //todo add regular expression for iran card numbers
    }
}
