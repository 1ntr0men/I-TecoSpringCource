package ru.iteco.itecospringcource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.itecospringcource.model.entity.StatusEntity;

public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {
}
