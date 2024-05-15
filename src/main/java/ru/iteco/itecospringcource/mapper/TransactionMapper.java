package ru.iteco.itecospringcource.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.iteco.itecospringcource.model.TransactionDto;
import ru.iteco.itecospringcource.model.entity.TransactionEntity;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "sourceBankBookId", source = "sourceBankBookId.id")
    @Mapping(target = "targetBankBookId", source = "targetBankBookId.id")
    @Mapping(target = "status", source = "status.id")
    TransactionDto mapToDto(TransactionEntity transactionEntity);

    @Mapping(target = "sourceBankBookId.id", source = "sourceBankBookId")
    @Mapping(target = "targetBankBookId.id", source = "targetBankBookId")
    @Mapping(target = "status.id", source = "status")
    TransactionEntity mapToEntity(TransactionDto transactionDto);
}
