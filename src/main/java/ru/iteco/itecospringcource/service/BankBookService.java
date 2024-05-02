package ru.iteco.itecospringcource.service;

import ru.iteco.itecospringcource.model.BankBookDto;

import java.util.List;

public interface BankBookService {

    BankBookDto getBankBookById(Integer id);
    List<BankBookDto> getAllUserBankBooks(Integer userId);
    BankBookDto createBankBook(BankBookDto bankBookDto);
    BankBookDto updateBankBook(BankBookDto bankBookDto);
    void deleteBankBook(Integer id);
    void deleteUsersBankBooks(Integer userId);

}
