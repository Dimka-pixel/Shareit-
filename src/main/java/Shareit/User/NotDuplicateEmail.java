package Shareit.User;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DuplicateEmailValidator.class)
@Documented
public @interface NotDuplicateEmail {
    

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "The user with this Email already was registration";
}
