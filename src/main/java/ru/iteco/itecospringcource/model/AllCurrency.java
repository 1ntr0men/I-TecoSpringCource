package ru.iteco.itecospringcource.model;

import lombok.Data;

import java.util.Map;

@Data
public class AllCurrency {

    private String base;
    private String date;
    private Map<String, String> rates;

}
