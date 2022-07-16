package kz.iitu.itse1908.daniyal.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CarPriceValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CarPriceConstraint {
    String message() default "Invalid price of car";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
