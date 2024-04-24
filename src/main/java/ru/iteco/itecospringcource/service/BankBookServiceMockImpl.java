package ru.iteco.itecospringcource.service;

import org.springframework.stereotype.Service;
import ru.iteco.itecospringcource.model.BankBook;

import java.util.List;

@Service
public class BankBookServiceMockImpl implements BankBookService {
    @Override
    public List<BankBook> getBankBooksById(Integer id) {
        return List.of();
    }
}
