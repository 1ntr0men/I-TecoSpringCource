package ru.iteco.itecospringcource.model.exception;

public class BankBookWithCurrencyAlreadyHaveException extends RuntimeException{

    public BankBookWithCurrencyAlreadyHaveException(String message) {
        super(message);
    }

}
