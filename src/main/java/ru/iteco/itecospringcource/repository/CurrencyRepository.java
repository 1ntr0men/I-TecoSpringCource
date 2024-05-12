package ru.iteco.itecospringcource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.itecospringcource.model.entity.CurrencyEntity;

public interface CurrencyRepository  extends JpaRepository<CurrencyEntity, Integer> {

    CurrencyEntity findByName(String name);

}
