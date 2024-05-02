package ru.iteco.itecospringcource.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.iteco.itecospringcource.model.BankBookDto;
import ru.iteco.itecospringcource.model.exception.BankBookNotFoundException;
import ru.iteco.itecospringcource.model.exception.BankBookNumberCannotBeModifiedException;
import ru.iteco.itecospringcource.model.exception.BankBookWithCurrencyAlreadyHaveException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class BankBookServiceImpl implements BankBookService {

    private final Map<Integer, BankBookDto> bankBookDtoMap = new ConcurrentHashMap<>();
    AtomicInteger sequenceId = new AtomicInteger(1);
    AtomicInteger sequenceUserId = new AtomicInteger(1);

    @PostConstruct
    public void init() {
        int id = sequenceId.getAndIncrement();
        int userId = sequenceUserId.getAndIncrement();
        bankBookDtoMap.put(id, BankBookDto.builder()
                .id(id)
                .userId(userId)
                .number("initUserNumber")
                .amount(new BigDecimal(0))
                .currency("RUB")
                .build());
    }

    @Override
    public BankBookDto getBankBookById(Integer id) {
        BankBookDto bankBookDto = bankBookDtoMap.get(id);
        if (bankBookDto == null) {
            throw new BankBookNotFoundException("Счет не найден!");
        }
        return bankBookDto;
    }

    @Override
    public List<BankBookDto> getAllUserBankBooks(Integer userId) {
        List<BankBookDto> userBankBooks = new ArrayList<>();

        for (BankBookDto bankBook : bankBookDtoMap.values()) {
            if (bankBook.getUserId().equals(userId)) {
                userBankBooks.add(bankBook);
            }
        }

        if (userBankBooks.isEmpty()) {
            throw new BankBookNotFoundException("Для данного пользователья нет счетов");
        }
        return userBankBooks;
    }

    @Override
    public BankBookDto createBankBook(BankBookDto bankBookDto) {
        boolean hasBankBook = bankBookDtoMap.values().stream()
                .anyMatch(bankBook -> bankBook.getUserId().equals(bankBookDto.getUserId())
                && bankBook.getNumber().equals(bankBookDto.getNumber())
                && bankBook.getCurrency().equals(bankBookDto.getCurrency()));

        if (hasBankBook) {
            throw new BankBookWithCurrencyAlreadyHaveException("Счет с данной валютой уже имеется!");
        }

        int id = sequenceId.getAndIncrement();
        bankBookDto.setId(id);
        bankBookDtoMap.put(id, bankBookDto);
        return bankBookDto;
    }

    @Override
    public BankBookDto updateBankBook(BankBookDto bankBookDto) {
        BankBookDto bankBookDtoFromMap = bankBookDtoMap.get(bankBookDto.getId());

        if (bankBookDtoFromMap == null) {
            throw new BankBookNotFoundException("Лицевой счет не найден!");
        }
        if (!bankBookDtoFromMap.getNumber().equals(bankBookDto.getNumber())) {
            throw new BankBookNumberCannotBeModifiedException("Номер лицевого счета менять нельзя!");
        }

        bankBookDtoMap.put(bankBookDto.getId(), bankBookDto);

        return bankBookDto;
    }

    @Override
    public void deleteBankBook(Integer id) {
        bankBookDtoMap.remove(id);
    }

    @Override
    public void deleteUsersBankBooks(Integer userId) {
        List<BankBookDto> userBankBooks = new ArrayList<>();
        Iterator<Map.Entry<Integer, BankBookDto>> iterator = bankBookDtoMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, BankBookDto> entry = iterator.next();
            BankBookDto bankBook = entry.getValue();
            if (bankBook.getUserId().equals(userId)) {
                userBankBooks.add(bankBook);
                iterator.remove();
            }
        }
    }

}
