package ru.iteco.itecospringcource.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.iteco.itecospringcource.model.annotation.CheckRequest;

@Service
public class ExternalInfoProcess implements Process {

    @Value("${id-not-process}")
    private int fromConfigValue;

    @Override
    @CheckRequest
    public boolean run(ExternalInfo externalInfo) {
        System.out.println("Запуск обработки информации для ExternalInfo с ID: " + externalInfo.getId());

        if (externalInfo.getId() == fromConfigValue) {
            System.out.println("ID совпадает со значением из конфигурации: " + fromConfigValue);
            throw new RuntimeException("ID совпадает со значением из конфигурации: " + fromConfigValue);
        } else {
            System.out.println("ID не совпадает со значением");
            return true;
        }
    }
}
