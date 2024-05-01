package ru.iteco.itecospringcource.apect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AfterErrorAspect {

    @AfterThrowing(value = "within(ru.iteco.itecospringcource.*)", throwing = "exception")
    public void afterErrorAdvice(JoinPoint.StaticPart staticPart, Exception exception) {
        log.info("afterErrorAdvice:: After throw {} with exception {}", staticPart.toShortString(), exception.getMessage());
    }

}
