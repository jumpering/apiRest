package com.pestmonitors.pestmonitors.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DNIValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DNI {
    String message() default "Invalid DNI Number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
