package com.openclassrooms.watchlist.validators;

import com.openclassrooms.watchlist.annotations.Priority;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriorityValidator implements ConstraintValidator<Priority, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        return value.trim().length() == 1 && "LMH".contains(value.toUpperCase());
    }
}
