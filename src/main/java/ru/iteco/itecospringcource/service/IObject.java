package ru.iteco.itecospringcource.service;

import ru.iteco.itecospringcource.model.annotation.EncryptResult;

public interface IObject {

    @EncryptResult
    String getInfo();

}
