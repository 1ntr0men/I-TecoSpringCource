package ru.iteco.itecospringcource.repository;

import org.springframework.stereotype.Component;

@Component
public class ExternalRepository {

    public String getInfo() {
        return "EXTERNAL_INFO";
    }

}
