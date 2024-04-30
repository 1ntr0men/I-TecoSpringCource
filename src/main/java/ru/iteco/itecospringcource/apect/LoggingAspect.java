package ru.iteco.itecospringcource.apect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Slf4j
@Component
@Aspect
public class LoggingAspect {

    @Before("allGetMethod() || allSetMethod()")
    public void beforeAllGetOrSetMethodAdvice(JoinPoint joinPoint) {

        log.info("beforeAllGetOrSetMethodAdvice:: Call method: {} with arguments {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @Pointcut("execution(public * get*(..))")
    public void allGetMethod() {
    }

    @Pointcut("execution(public * set*(..))")
    public void allSetMethod() {
    }

}
