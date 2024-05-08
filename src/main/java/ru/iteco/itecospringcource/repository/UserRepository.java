package ru.iteco.itecospringcource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.itecospringcource.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
