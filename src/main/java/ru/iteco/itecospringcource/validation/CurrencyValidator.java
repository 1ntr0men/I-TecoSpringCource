package ru.iteco.itecospringcource.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {

    private static final Set<String> CURRENCY = Set.of("RUB", "EUR", "USD", "GBP");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return CURRENCY.contains(value);
    }

}
