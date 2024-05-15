package ru.iteco.itecospringcource.service;

import ru.iteco.itecospringcource.model.TransactionDto;

import java.math.BigDecimal;

public interface TransactionService {

    TransactionDto createTransaction(TransactionDto transactionDto);
    TransactionDto transactionBetweenBankBooks(Integer bankBookId1, Integer bankBookId2, BigDecimal amount);
    TransactionDto transactionBetweenUsers(Integer userEntity1, Integer userEntity2, BigDecimal amount);

}
