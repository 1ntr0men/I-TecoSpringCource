package ru.iteco.itecospringcource.config;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import ru.iteco.itecospringcource.model.annotation.CacheResult;

import java.lang.reflect.Method;


public class BeanFactoryPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (AnnotationUtils.findAnnotation(bean.getClass(), Scope.class) != null && "prototype".equals(AnnotationUtils.findAnnotation(bean.getClass(), Scope.class).value())) {
            System.out.println("Bean '" + beanName + "' имеет scope со значением prototype");

            for (Method method : bean.getClass().getMethods()) {
                if (method.isAnnotationPresent(CacheResult.class)) {
                    System.out.println("Метод '" + method.getName() + "' бина: '" + beanName + "' имеет аннотацию @CacheResult.");
                }
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }
}
