package kz.iitu.itse1908.daniyal.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(value={ElementType.METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR,PARAMETER,TYPE_USE})
@Retention(value=RUNTIME)
@Documented
@Constraint(validatedBy={})
public @interface AssertTrue {
    String message() default "ERROR!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
