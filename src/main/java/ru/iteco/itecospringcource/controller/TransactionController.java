package ru.iteco.itecospringcource.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.iteco.itecospringcource.model.TransactionDto;
import ru.iteco.itecospringcource.service.TransactionService;

import java.math.BigDecimal;

@Validated
@RestController
@RequestMapping("/rest/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDto> createUser(@Valid @RequestBody TransactionDto transactionDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(transactionDto));
    }

    @GetMapping("/transactionBetweenBankBooks")
    public ResponseEntity<TransactionDto> getTransactionBetweenBankBooks(@RequestParam Integer bankBookId1,
                                                                            @RequestParam Integer bankBookId2,
                                                                            @RequestParam Integer amount) {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.transactionBetweenBankBooks(bankBookId1, bankBookId2, BigDecimal.valueOf(amount)));
    }

    @GetMapping("/transactionBetweenUsers")
    public ResponseEntity<TransactionDto> getTransactionBetweenUsers(@RequestParam Integer userId1,
                                                                         @RequestParam Integer userId2,
                                                                         @RequestParam Integer amount) {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.transactionBetweenUsers(userId1, userId2, BigDecimal.valueOf(amount)));
    }
}
