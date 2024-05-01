package ru.iteco.itecospringcource.apect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class AnnotationCacheResultAspect {

    private final Map<String, Object> cache = new HashMap<>();

    @Around("allCacheResultAnnotations()")
    public Object beforeAllAnnotationsCacheResultAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("beforeAllAnnotationsCacheResultAdvice:: call method with mark @CacheResult");

        String key = generateCacheKey(proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getArgs());
        System.out.println("Вызов " + proceedingJoinPoint.getSignature().getName() + ", вызов должен быть закэширован");
        if (cache.containsKey(key)) {
            System.out.println("Returning cached result for " + key);
            return cache.get(key);
        }

        Object result = proceedingJoinPoint.proceed();
        cache.put(key, result);
        System.out.println("Caching result for " + key);
        return result;
    }

    @Pointcut("@annotation(ru.iteco.itecospringcource.model.annotation.CacheResult)")
    public void allCacheResultAnnotations() {}

    private String generateCacheKey(String methodName, Object[] args) {
        StringBuilder keyBuilder = new StringBuilder(methodName);
        keyBuilder.append(".").append(methodName).append("(");
        for (Object arg : args) {
            keyBuilder.append(arg != null ? arg.toString() : "null").append(",");
        }
        if (args.length > 0) {
            keyBuilder.setLength(keyBuilder.length() - 1);
        }
        keyBuilder.append(")");
        return keyBuilder.toString();
    }

}
