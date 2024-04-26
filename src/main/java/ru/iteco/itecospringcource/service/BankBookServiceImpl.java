package ru.iteco.itecospringcource.service;

import org.springframework.stereotype.Service;
import ru.iteco.itecospringcource.model.BankBook;
import ru.iteco.itecospringcource.model.annotation.InjectRandom;

import java.util.List;

@Service
public class BankBookServiceImpl implements BankBookService {

    @InjectRandom(max = 100)
    private Integer number;

    @Override
    public List<BankBook> getBankBooksById(Integer id) {
        BankBook bankBook = new BankBook();

        bankBook.setNumber(number.longValue());
        bankBook.setUserId(id);

        return List.of(bankBook);
    }

}
