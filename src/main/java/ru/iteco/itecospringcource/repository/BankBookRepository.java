package ru.iteco.itecospringcource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.itecospringcource.model.entity.BankBookEntity;
import ru.iteco.itecospringcource.model.entity.CurrencyEntity;

import java.util.List;
import java.util.Optional;

public interface BankBookRepository extends JpaRepository<BankBookEntity, Integer> {

    List<BankBookEntity> findByUserId(Integer userId);

    Optional<BankBookEntity> findByUserIdAndNumberAndCurrency(Integer userId, String number, CurrencyEntity currency);

    void deleteAllByUserId(Integer userId);
}
