package ru.iteco.itecospringcource.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class Flow {

    private final ExternalService externalService;
    private final Process process;

    public Flow(ExternalService externalService, @Lazy Process process) {
        this.externalService = externalService;
        this.process = process;
    }

    public void run(Integer id) {

        ExternalInfo externalInfo = externalService.getExternalInfo(id);

        if (externalInfo != null) {
            process.run(externalInfo);
        } else {
            System.out.println(externalService.getClass());
        }

    }

}
