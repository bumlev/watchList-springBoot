package com.openclassrooms.watchlist.validators;

import com.openclassrooms.watchlist.annotations.Rating;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RatingValidator implements ConstraintValidator<Rating, String> {

    @Override
    public boolean isValid(String Value, ConstraintValidatorContext constraintValidatorContext) {

        double number;
        try {
            number = Double.parseDouble(Value);
        } catch (NumberFormatException e) {
            return false;
        }
        return !(number > 10) && !(number < 5);
    }
}
