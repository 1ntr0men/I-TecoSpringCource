package ru.iteco.itecospringcource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.itecospringcource.model.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
