package com.example.tpecommerce.util.annotation;


import com.example.tpecommerce.util.annotation.validator.PasswordConstraintsValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordConstraintsValidator.class)
public @interface Password {
    String message() default "Password should be valid ";

    Class[] groups() default {};

    Class[] payload() default {};
}
