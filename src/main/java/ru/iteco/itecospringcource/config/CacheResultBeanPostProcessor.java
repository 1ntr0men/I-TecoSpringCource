package ru.iteco.itecospringcource.config;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import ru.iteco.itecospringcource.model.annotation.CacheResult;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheResultBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Object> cache = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("Реализация задания: Так же метод должен быть помечен аннотацией @CacheResult" +
                " (ее логику реализовать самостоятельно, кешировать по имени класса, метода и аргументов)," +
                " которая кеширует результат выполнения метода.");

        for (Method method : bean.getClass().getMethods()) {
            if (method.isAnnotationPresent(CacheResult.class)) {
                System.out.println("Бин должен быть закэширован");
                ProxyFactory proxyFactory = new ProxyFactory(bean);
                proxyFactory.addAdvice((MethodInterceptor) invocation -> {
                    Object proceed = invocation.proceed();
                    for (Method declaredMethod : invocation.getThis().getClass().getDeclaredMethods()) {
                        if (invocation.getMethod().getName().equals(declaredMethod.getName()) && AnnotationUtils.findAnnotation(declaredMethod, CacheResult.class) != null) {
                            String key = generateCacheKey(invocation.getMethod(), invocation.getArguments());
                            System.out.println("Вызов " + invocation.getMethod().getName() + ", вызов должен быть закэширован");
                            if (cache.containsKey(key)) {
                                System.out.println("Returning cached result for " + key);
                                return cache.get(key);
                            }

                            Object result = invocation.proceed();
                            cache.put(key, result);
                            System.out.println("Caching result for " + key);
                            return result;
                        }
                    }
                    return proceed;
                });
                return proxyFactory.getProxy();
            }
        }

        return bean;
    }

    private String generateCacheKey(Method method, Object[] args) {
        StringBuilder keyBuilder = new StringBuilder(method.getDeclaringClass().getName());
        keyBuilder.append(".").append(method.getName()).append("(");
        for (Object arg : args) {
            keyBuilder.append(arg != null ? arg.toString() : "null").append(",");
        }
        if (args.length > 0) {
            keyBuilder.setLength(keyBuilder.length() - 1);  // Remove trailing comma
        }
        keyBuilder.append(")");
        return keyBuilder.toString();
    }

}
