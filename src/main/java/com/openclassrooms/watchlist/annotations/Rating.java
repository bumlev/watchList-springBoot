package com.openclassrooms.watchlist.annotations;


import com.openclassrooms.watchlist.validators.RatingValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RatingValidator.class)

public @interface Rating {

    String message() default "Rating should be a number between 5-10";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
