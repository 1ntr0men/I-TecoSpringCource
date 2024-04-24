package ru.iteco.itecospringcource.service;

import ru.iteco.itecospringcource.model.AccountInfo;

public interface AccountService {
    AccountInfo getAccountInfoById(Integer id);

}
