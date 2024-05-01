package ru.iteco.itecospringcource.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import ru.iteco.itecospringcource.model.annotation.InjectRandom;

import java.lang.reflect.Field;

public class InjectRandomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("beanName: " + beanName + "; beanClass: " + bean.getClass().getSimpleName());

        for (Field declaredField : bean.getClass().getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(InjectRandom.class)) {
                declaredField.setAccessible(true);
                InjectRandom annotation = declaredField.getAnnotation(InjectRandom.class);
                int random = getRandom(annotation.min(), annotation.max());
                System.out.println("Set random value in " + declaredField.getName() + "; Value: " + random);
                ReflectionUtils.setField(declaredField, bean, random);
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }
}
