package ru.iteco.itecospringcource.service;

import org.springframework.stereotype.Component;
import ru.iteco.itecospringcource.mapper.TransactionMapper;
import ru.iteco.itecospringcource.model.BankBookDto;
import ru.iteco.itecospringcource.model.TransactionDto;
import ru.iteco.itecospringcource.model.entity.BankBookEntity;
import ru.iteco.itecospringcource.model.entity.StatusEntity;
import ru.iteco.itecospringcource.model.entity.TransactionEntity;
import ru.iteco.itecospringcource.model.entity.UserEntity;
import ru.iteco.itecospringcource.repository.BankBookRepository;
import ru.iteco.itecospringcource.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class TransactionServiceRepository implements TransactionService {

    private final TransactionMapper transactionMapper;

    private final BankBookRepository bankBookRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceRepository(TransactionMapper transactionMapper,
                                        BankBookRepository bankBookRepository,
                                        TransactionRepository transactionRepository) {
        this.transactionMapper = transactionMapper;
        this.bankBookRepository = bankBookRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        TransactionEntity transactionEntity = transactionMapper.mapToEntity(transactionDto);
        return transactionMapper.mapToDto(transactionRepository.save(transactionEntity));
    }

    @Override
    public TransactionDto transactionBetweenBankBooks(Integer bankBookId1, Integer bankBookId2, BigDecimal amount) {

        TransactionEntity transactionEntity = transactionMapper.mapToEntity(
                TransactionDto.builder()
                        .sourceBankBookId(bankBookId1)
                        .targetBankBookId(bankBookId2)
                        .initiationDate(LocalDateTime.now())
                        .amount(amount)
                        .status(1)
                        .build()
        );

        transactionRepository.save(transactionEntity);

        BankBookEntity bankBookEntity1 = bankBookRepository.findById(bankBookId1).get();
        BankBookEntity bankBookEntity2 = bankBookRepository.findById(bankBookId2).get();

        if (bankBookEntity1.getCurrency() == bankBookEntity2.getCurrency() &&
                bankBookEntity1.getAmount().compareTo(amount) >= 0) {
            bankBookEntity1.setAmount(bankBookEntity1.getAmount().subtract(amount));
            bankBookEntity2.setAmount(bankBookEntity2.getAmount().add(amount));

            bankBookRepository.save(bankBookEntity1);
            bankBookRepository.save(bankBookEntity2);

            transactionEntity.setCompletionDate(LocalDateTime.now());
            transactionEntity.setStatus(StatusEntity.builder().id(2).build());
        } else {
            transactionEntity.setCompletionDate(LocalDateTime.now());
            transactionEntity.setStatus(StatusEntity.builder().id(3).build());
        }

        return transactionMapper.mapToDto(transactionRepository.save(transactionEntity));
    }

    @Override
    public TransactionDto transactionBetweenUsers(Integer userId1, Integer userId2, BigDecimal amount) {

        BankBookEntity bankBookEntity1 = bankBookRepository.findAllByUserId(userId1).getFirst();
        BankBookEntity bankBookEntity2 = bankBookRepository.findAllByUserId(userId2).getFirst();

        TransactionEntity transactionEntity = transactionMapper.mapToEntity(
                TransactionDto.builder()
                        .sourceBankBookId(bankBookEntity1.getId())
                        .targetBankBookId(bankBookEntity2.getId())
                        .initiationDate(LocalDateTime.now())
                        .amount(amount)
                        .status(1)
                        .build()
        );

        transactionRepository.save(transactionEntity);

        if (bankBookEntity1.getCurrency() == bankBookEntity2.getCurrency() &&
                bankBookEntity1.getAmount().compareTo(amount) >= 0) {
            bankBookEntity1.setAmount(bankBookEntity1.getAmount().subtract(amount));
            bankBookEntity2.setAmount(bankBookEntity2.getAmount().add(amount));

            bankBookRepository.save(bankBookEntity1);
            bankBookRepository.save(bankBookEntity2);

            transactionEntity.setCompletionDate(LocalDateTime.now());
            transactionEntity.setStatus(StatusEntity.builder().id(2).build());
        } else {
            transactionEntity.setCompletionDate(LocalDateTime.now());
            transactionEntity.setStatus(StatusEntity.builder().id(3).build());
        }

        return transactionMapper.mapToDto(transactionRepository.save(transactionEntity));
    }
}
