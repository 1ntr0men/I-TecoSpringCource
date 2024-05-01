package ru.iteco.itecospringcource.apect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ServicePackageAspect {

    @Before("allServiceMethods()")
    public void beforeAllServiceMethodsAdvice() {
        log.info("beforeAllServiceMethodsAdvice:: call method from SERVICE package");
    }

    @After("allServiceMethods()")
    public void afterAllServiceMethodsAdvice(JoinPoint joinPoint) {
        log.info("afterAllServiceMethodsAdvice:: After {} with args {}", joinPoint.toShortString(), joinPoint.getArgs() );
    }

    @Pointcut("within(ru.iteco.itecospringcource.service.*)")
    public void allServiceMethods() {}

}
