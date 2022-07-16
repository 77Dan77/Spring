package kz.iitu.itse1908.daniyal.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CarPriceValidator implements ConstraintValidator<CarPriceConstraint, Long> {

    @Override
    public void initialize(CarPriceConstraint constraintAnnotation) {
    }

    @AssertTrue
    @Override
    public boolean isValid(Long price,
        ConstraintValidatorContext cxt) {
            return price != null && (price > 99000) && (price < 999999999);
    }
}
