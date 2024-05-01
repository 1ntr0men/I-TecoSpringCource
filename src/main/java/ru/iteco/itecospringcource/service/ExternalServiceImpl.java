package ru.iteco.itecospringcource.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import ru.iteco.itecospringcource.model.annotation.CacheResult;

import java.util.HashMap;

@Service
public class ExternalServiceImpl implements ExternalService {

    HashMap<Integer, ExternalInfo> dataMap;

    @PostConstruct
    public void init() {
        dataMap = new HashMap<>();
        dataMap.put(1, new ExternalInfo(1, null));
        dataMap.put(2, new ExternalInfo(2, "hasInfo"));
        dataMap.put(3, new ExternalInfo(3, "info"));
        dataMap.put(4, new ExternalInfo(4, "information"));
        System.out.println("Реализация задания: ExternalServiceImpl должен содержать HashMap<Integer, ExternalInfo>, наполненный тестовыми данными при инициализации бина.");
    }

    @PreDestroy
    public void cleanup() {
        dataMap.clear();
        System.out.println("Реализация задания: HashMap должен очищаться перед закрытием контекста (добавить лог).");
    }

    @Override
    @CacheResult
    public ExternalInfo getExternalInfo(Integer id) {
        System.out.println("Реализация задания: Метод getExternalInfo должен получать ExternalInfo по id из HashMap (добавить лог вызова метода).");

        System.out.println("getExternalInfo был вызван с айди: " + id);
        ExternalInfo externalInfo = dataMap.get(id);

        if (externalInfo == null) {
            throw new RuntimeException("Не найдено!");
        } else {
            return externalInfo;
        }

    }

    @Override
    public void saveExternalInfo(ExternalInfo externalInfo) {
        dataMap.put(externalInfo.getId(), externalInfo);
    }
}
