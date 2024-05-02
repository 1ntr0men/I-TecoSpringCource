package ru.iteco.itecospringcource.model.exception;

public class BankBookNotFoundException extends RuntimeException {

    public BankBookNotFoundException(String message) {
        super(message);
    }

}
