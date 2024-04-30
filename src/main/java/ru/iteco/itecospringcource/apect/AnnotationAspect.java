package ru.iteco.itecospringcource.apect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
@Slf4j
public class AnnotationAspect {

    @Before("@annotation(ru.iteco.itecospringcource.model.annotation.CacheResult)")
    public void beforeAllAnnotationsCacheResultAdvice() {
        log.info("beforeAllAnnotationsCacheResultAdvice:: call method with mark @CacheResult");
    }

}
