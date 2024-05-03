package ru.iteco.itecospringcource.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.iteco.itecospringcource.model.BankBookDto;
import ru.iteco.itecospringcource.service.BankBookService;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
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

    @GetMapping("/{bankBookId}")
    public ResponseEntity<BankBookDto> getBankBookById(@PathVariable Integer bankBookId) {
        return ResponseEntity.ok(bankBookService.getBankBookById(bankBookId));
    }

    @PostMapping
    public ResponseEntity<BankBookDto> createBankBook(@RequestBody BankBookDto bankBookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankBookService.createBankBook(bankBookDto));
    }

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
