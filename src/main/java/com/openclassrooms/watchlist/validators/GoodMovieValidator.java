package com.openclassrooms.watchlist.validators;

import com.openclassrooms.watchlist.domain.WatchListItem;
import com.openclassrooms.watchlist.annotations.GoodMovie;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GoodMovieValidator implements ConstraintValidator<GoodMovie, WatchListItem> {

    @Override
    public boolean isValid(WatchListItem watchListItem ,
                           ConstraintValidatorContext constraintValidatorContext) {

        return !(Double.parseDouble(watchListItem.getRating()) >=8
                    && "L".equalsIgnoreCase(watchListItem.getPriority().trim()));
    }
}
