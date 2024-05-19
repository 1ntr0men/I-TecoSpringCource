package ru.iteco.itecospringcource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iteco.itecospringcource.model.AllCurrency;
import ru.iteco.itecospringcource.service.ExchangeApi;

@RestController("/exchange")
public class ExchangeController {

    private final ExchangeApi exchangeApi;

    public ExchangeController(ExchangeApi exchangeApi) {
        this.exchangeApi = exchangeApi;
    }

    @GetMapping("/all-currency")
    public AllCurrency getAllCurrency() {
        return exchangeApi.getAllCurrency();
    }
}
