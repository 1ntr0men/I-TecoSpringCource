package ru.iteco.itecospringcource.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.iteco.itecospringcource.model.annotation.CacheResult;
import ru.iteco.itecospringcource.model.annotation.EncryptResult;
import ru.iteco.itecospringcource.model.annotation.InjectRandom;

@Component
@Scope("prototype")
public class ObjectValue implements IObject {

    @InjectRandom
    private int value1;

    @InjectRandom(min = 10, max = 100)
    private int value2;

    @CacheResult
    @EncryptResult
    public String getInfo() {
        return "ObjectValue{" +
                "value1=" + value1 +
                "value2=" + value2 +
                "}";
    }
}
