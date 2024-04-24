package ru.iteco.itecospringcource.service;

import org.springframework.stereotype.Service;
import ru.iteco.itecospringcource.model.BankBook;

import java.util.List;

@Service
public class BankBookServiceImpl implements BankBookService {
    @Override
    public List<BankBook> getBankBooksById(Integer id) {
        BankBook bankBook = new BankBook();

        bankBook.setNumber(1L);
        bankBook.setUserId(id);

        return List.of(bankBook);
    }

}
