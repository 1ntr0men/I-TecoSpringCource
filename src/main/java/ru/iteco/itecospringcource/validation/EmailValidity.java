package ru.iteco.itecospringcource.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = EmailValidator.class)
public @interface EmailValidity {

    String message() default "Введен некорректный email пользователя";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
