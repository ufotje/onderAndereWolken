package client.entities.validation;

import client.entities.validation.implementation.DateAfterDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=DateAfterDateValidator.class)
public @interface ValidDateAfterDate {
    String[] value();
    String message() default "{ValidDateAfterDate.id}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
