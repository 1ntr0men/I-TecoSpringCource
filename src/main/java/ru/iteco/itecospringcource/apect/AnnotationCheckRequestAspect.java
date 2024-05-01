package ru.iteco.itecospringcource.apect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.iteco.itecospringcource.service.ExternalInfo;

@Aspect
@Component
@Slf4j
public class AnnotationCheckRequestAspect {

    @Value("${id-not-process}")
    private int fromConfigValue;

    @Around("allCheckRequestAnnotations(externalInfo)")
    public Object beforeAllAnnotationsCheckRequestAdvice(ProceedingJoinPoint proceedingJoinPoint, ExternalInfo externalInfo) throws Throwable {
        log.info("beforeAllAnnotationsCheckRequestAdvice:: call method with mark @CheckRequest");

        if (externalInfo.getId() == fromConfigValue) {
            return proceedingJoinPoint.proceed();
        } else {
            throw new RuntimeException("Decline access");
        }
    }

    @Pointcut("@annotation(ru.iteco.itecospringcource.model.annotation.CheckRequest) && args(externalInfo, ..)")
    public void allCheckRequestAnnotations(ExternalInfo externalInfo) {}

}
