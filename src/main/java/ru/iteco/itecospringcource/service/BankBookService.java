package ru.iteco.itecospringcource.service;

import ru.iteco.itecospringcource.model.BankBook;
import java.util.List;

public interface BankBookService {
    List<BankBook> getBankBooksById(Integer id);

}
