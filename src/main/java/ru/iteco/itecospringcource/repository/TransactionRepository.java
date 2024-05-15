package ru.iteco.itecospringcource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.itecospringcource.model.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
}
