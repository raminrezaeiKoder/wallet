package com.digitalwallet.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Integer count = 0;

        if (!s.matches("(?=\\S+$)")) // white spaces don’t allowed in the entire string.
            return false;
        else if (s.length() >= 8 && s.length() <= 32) {
            if (s.matches("\\d")) //represents a digit must occur at least once,
                count++;
            if (s.matches("[a-z]")) //represents a lowercase character must occur at least once
                count++;
            if (s.matches("[A-Z]")) //represents a uppercase character must occur at least once
                count++;
            if (s.matches("[@#$%^&+=~`]"))  //represents a special character must occur at least once
                count++;

        }

        return count >= 3;


    }
}
