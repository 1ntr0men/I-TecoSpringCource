package ru.iteco.itecospringcource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.itecospringcource.model.entity.UserAuthEntity;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuthEntity, Integer> {

    Optional<UserAuthEntity> findByUsername(String username);

}
