package ru.iteco.itecospringcource.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.iteco.itecospringcource.model.AccountInfo;
import ru.iteco.itecospringcource.model.BankBook;
import ru.iteco.itecospringcource.model.PersonalInfo;

import java.util.List;

@Service
@Primary
public class AccountServiceImpl implements AccountService {
    private final PersonalInformationService personalInformationService;
    private final List<BankBookService> bankBookServices;

    public AccountServiceImpl(@Lazy PersonalInformationService personalInformationService,
                              List<BankBookService> bankBookServices) {
        this.personalInformationService = personalInformationService;
        this.bankBookServices = bankBookServices;
    }

    @Override
    public AccountInfo getAccountInfoById(Integer id) {
        AccountInfo accountInfo = new AccountInfo();
        PersonalInfo personalInfo = personalInformationService.getPersonalInfoById(id);
        accountInfo.setPersonalInfo(personalInfo);

        for (BankBookService bankBookService : bankBookServices) {
            List<BankBook> bankBooks = bankBookService.getBankBooksById(id);
            System.out.println(bankBooks);

            if (!bankBooks.isEmpty()) {
                accountInfo.setBankBooks(bankBooks);
            }
        }

        return accountInfo;
    }

    @Override
    public String getPersonalInfoClass() {
        return personalInformationService.getClass().toString();
    }

}
