package ru.iteco.itecospringcource.service;

import lombok.Data;

@Data
public class ExternalInfo {

    Integer id;

    String info;

    public ExternalInfo(Integer id, String info) {
        this.id = id;
        this.info = info;
    }
}
