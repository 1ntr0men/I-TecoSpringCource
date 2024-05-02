package ru.iteco.itecospringcource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iteco.itecospringcource.model.ExternalInfo;
import ru.iteco.itecospringcource.service.ExternalService;

@RestController
class DefaultController {

    private final ExternalService externalService;

    DefaultController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping("/info")
    ExternalInfo getInfo() {
        return externalService.getInfo();
    }

}
