package ru.iteco.itecospringcource.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.iteco.itecospringcource.model.ExternalInfo;

@Slf4j
@Component
class ExternalServiceImpl implements ExternalService {

    @Override
    public ExternalInfo getInfo() {
        log.info("Call get info!");
        return ExternalInfo.builder().info("INFO!").build();
    }
}
