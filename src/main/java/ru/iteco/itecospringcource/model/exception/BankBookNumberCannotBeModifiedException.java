package ru.iteco.itecospringcource.model.exception;

public class BankBookNumberCannotBeModifiedException extends RuntimeException {

    public BankBookNumberCannotBeModifiedException(String message) {
        super(message);
    }

}
