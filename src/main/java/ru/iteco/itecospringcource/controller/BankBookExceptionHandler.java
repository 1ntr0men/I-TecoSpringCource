package ru.iteco.itecospringcource.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.iteco.itecospringcource.model.ErrorDto;
import ru.iteco.itecospringcource.model.exception.BankBookNotFoundException;
import ru.iteco.itecospringcource.model.exception.BankBookNumberCannotBeModifiedException;
import ru.iteco.itecospringcource.model.exception.BankBookWithCurrencyAlreadyHaveException;

@Slf4j
@RestControllerAdvice
public class BankBookExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BankBookNotFoundException.class)
    public ErrorDto handleBankBookNotFoundException(BankBookNotFoundException e) {
        log.error("ERROR!", e);
        return ErrorDto.builder()
                .status(HttpStatus.NOT_FOUND.name())
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BankBookNumberCannotBeModifiedException.class)
    public ErrorDto handleBankBookNumberCannotBeModifiedException(BankBookNumberCannotBeModifiedException e) {
        log.error("ERROR!", e);
        return ErrorDto.builder()
                .status(HttpStatus.NOT_FOUND.name())
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BankBookWithCurrencyAlreadyHaveException.class)
    public ErrorDto handleBankBookWithCurrencyAlreadyHaveException(BankBookWithCurrencyAlreadyHaveException e) {
        log.error("ERROR!", e);
        return ErrorDto.builder()
                .status(HttpStatus.NOT_FOUND.name())
                .message(e.getMessage())
                .build();
    }

}
