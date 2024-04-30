package ru.iteco.itecospringcource.service;

public interface ExternalService {

    ExternalInfo getExternalInfo(Integer id);

    void saveExternalInfo(ExternalInfo externalInfo);

}
