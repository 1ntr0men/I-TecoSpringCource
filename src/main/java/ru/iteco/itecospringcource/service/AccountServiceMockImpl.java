package ru.iteco.itecospringcource.service;

import org.springframework.stereotype.Service;
import ru.iteco.itecospringcource.model.AccountInfo;

@Service("accountServiceMockTestImpl")
public class AccountServiceMockImpl implements AccountService {
    @Override
    public AccountInfo getAccountInfoById(Integer id) {
        return new AccountInfo();
    }

    @Override
    public String getPersonalInfoClass() {
        return null;
    }

}
