package ru.iteco.itecospringcource.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.iteco.itecospringcource.model.BankBookDto;
import ru.iteco.itecospringcource.model.entity.BankBookEntity;

@Mapper(componentModel = "spring")
public interface BankBookMapper {

    @Mapping(target = "currency", source = "currency.name")
    BankBookDto mapToDto(BankBookEntity bankBookEntity);

    @Mapping(target = "currency.name", source = "currency")
    BankBookEntity mapToEntity(BankBookDto bankBookDto);
}
