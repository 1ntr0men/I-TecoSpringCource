package ru.iteco.itecospringcource.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.iteco.itecospringcource.model.BankBookDto;
import ru.iteco.itecospringcource.service.BankBookService;
import ru.iteco.itecospringcource.validation.Update;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/bank-book")
public class BankBookController {

    private final BankBookService bankBookService;


    public BankBookController(BankBookService bankBookService) {
        this.bankBookService = bankBookService;
    }

    @GetMapping("/by-user-id")
    public ResponseEntity<List<BankBookDto>> getBankBooksByUserId(@CookieValue Integer userId, @RequestHeader Map<String, String> headers) {
        log.info("Call with headers: {}", headers);
        return ResponseEntity.ok(bankBookService.getAllUserBankBooks(userId));
    }

    @GetMapping("/{Id}")
    public ResponseEntity<BankBookDto> getBankBookById(@Min(value = 0L, message = "Больше 0!") @PathVariable Integer Id) {
        return ResponseEntity.ok(bankBookService.getBankBookById(Id));
    }

    @PostMapping
    public ResponseEntity<BankBookDto> createBankBook(@Valid @RequestBody BankBookDto bankBookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankBookService.createBankBook(bankBookDto));
    }

    @Validated(Update.class)
    @PutMapping
    public BankBookDto updateBankBook(@RequestBody BankBookDto bankBookDto) {
        return bankBookService.updateBankBook(bankBookDto);
    }

    @DeleteMapping({"/{bankBookId}", "/"})
    public void deleteBankBook(@PathVariable Integer bankBookId) {
        bankBookService.deleteBankBook(bankBookId);
    }

    @DeleteMapping({"/by-user-id/{userId}", "/by-user-id/"})
    public void deleteBankBookByUserId(@PathVariable Integer userId) {
        bankBookService.deleteUsersBankBooks(userId);
    }
}
