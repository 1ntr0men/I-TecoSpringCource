package ru.iteco.itecospringcource.service;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.iteco.itecospringcource.mapper.BankBookMapper;
import ru.iteco.itecospringcource.model.BankBookDto;
import ru.iteco.itecospringcource.model.entity.BankBookEntity;
import ru.iteco.itecospringcource.model.entity.CurrencyEntity;
import ru.iteco.itecospringcource.model.exception.BankBookNotFoundException;
import ru.iteco.itecospringcource.model.exception.BankBookNumberCannotBeModifiedException;
import ru.iteco.itecospringcource.model.exception.BankBookWithCurrencyAlreadyHaveException;
import ru.iteco.itecospringcource.repository.BankBookRepository;
import ru.iteco.itecospringcource.repository.CurrencyRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BankBookServiceImpl implements BankBookService {

    private final Map<Integer, BankBookDto> bankBookDtoMap = new ConcurrentHashMap<>();

    private final BankBookRepository bankBookRepository;
    private final CurrencyRepository currencyRepository;
    private final BankBookMapper bankBookMapper;

    public BankBookServiceImpl(BankBookRepository bankBookRepository, CurrencyRepository currencyRepository,
                               BankBookMapper bankBookMapper) {
        this.bankBookRepository = bankBookRepository;
        this.currencyRepository = currencyRepository;
        this.bankBookMapper = bankBookMapper;
    }

    @Override
    public BankBookDto getBankBookById(Integer id) {
        return bankBookRepository.findById(id)
                .map(bankBookMapper::mapToDto)
                .orElseThrow(() -> new BankBookNotFoundException("Счет не найден!"));
    }

    @Override
    public List<BankBookDto> getAllUserBankBooks(Integer userId) {
        List<BankBookDto> bankBookDtos = bankBookRepository.findAllByUserId(userId).stream()
                .map(bankBookMapper::mapToDto)
                .toList();
        if (CollectionUtils.isEmpty(bankBookDtos)) {
            throw new BankBookNotFoundException("Для данного пользователя нет счетов");
        }
        return bankBookDtos;
    }

    @Override
    public BankBookDto createBankBook(BankBookDto bankBookDto) {
        CurrencyEntity currency = currencyRepository.findByName(bankBookDto.getCurrency());
        Optional<BankBookEntity> bankBookEntityOpt = bankBookRepository.findByUserIdAndNumberAndCurrency(
                bankBookDto.getUserId(),
                bankBookDto.getNumber(),
                currency
        );
        if (bankBookEntityOpt.isPresent()) {
            throw new BankBookWithCurrencyAlreadyHaveException("Счет с данной валютой уже имеется!");
        }
        BankBookEntity bankBookEntity = bankBookMapper.mapToEntity(bankBookDto);
        bankBookEntity.setCurrency(currency);
        return bankBookMapper.mapToDto(
                bankBookRepository.save(bankBookEntity)
        );
    }

    @Override
    public BankBookDto updateBankBook(BankBookDto bankBookDto) {
        BankBookEntity bankBookEntity = bankBookRepository.findById(bankBookDto.getId())
                .orElseThrow(() -> new BankBookNotFoundException("Лицевой счет не найден!"));

        if (!bankBookEntity.getNumber().equals(bankBookDto.getNumber())) {
            throw new BankBookNumberCannotBeModifiedException("Номер лицевого счета менять нельзя!");
        }

        CurrencyEntity currency = currencyRepository.findByName(bankBookDto.getCurrency());

        bankBookEntity = bankBookMapper.mapToEntity(bankBookDto);
        bankBookEntity.setCurrency(currency);
        return bankBookMapper.mapToDto(
                bankBookRepository.save(bankBookEntity)
        );
    }

    @Override
    public void deleteBankBook(Integer id) {
        bankBookRepository.deleteById(id);
    }

    @Override
    public void deleteUsersBankBooks(Integer userId) {
        bankBookRepository.deleteAllByUserId(userId);
    }

}
