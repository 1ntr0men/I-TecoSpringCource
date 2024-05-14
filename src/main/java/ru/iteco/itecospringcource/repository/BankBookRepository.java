package ru.iteco.itecospringcource.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import ru.iteco.itecospringcource.model.entity.BankBookEntity;
import ru.iteco.itecospringcource.model.entity.CurrencyEntity;

import java.util.List;
import java.util.Optional;

public interface BankBookRepository extends JpaRepository<BankBookEntity, Integer> {

    List<BankBookEntity> findAllByUserId(Integer userId);

    Optional<BankBookEntity> findByUserIdAndNumberAndCurrency(Integer userId, String number, CurrencyEntity currency);

    void deleteAllByUserId(Integer userId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select bbe from BankBookEntity bbe where bbe.number = :id")
    Optional<BankBookEntity> lockById(@Param("id") Integer id);

}
