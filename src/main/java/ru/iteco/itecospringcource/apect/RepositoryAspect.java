package ru.iteco.itecospringcource.apect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RepositoryAspect {

    @Before("within(ru.iteco.itecospringcource.repository.*)")
    public void beforeAllRepositoryAdvice() {
        log.info("beforeAllRepositoryAdvice:: call method from repository package");
    }

}
