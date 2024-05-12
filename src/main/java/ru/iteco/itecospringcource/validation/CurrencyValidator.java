package ru.iteco.itecospringcource.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.iteco.itecospringcource.repository.CurrencyRepository;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {

    private final CurrencyRepository currencyRepository;

    public CurrencyValidator(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return currencyRepository.findByName(value) != null;
    }

}
