package ru.iteco.itecospringcource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.iteco.itecospringcource.model.AccountInfo;
import ru.iteco.itecospringcource.service.*;

@ComponentScan
@PropertySource("classpath:application.properties")
public class AccountBankApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AccountBankApplication.class);
        AccountService accountService = applicationContext.getBean(AccountService.class);
        AccountInfo accountInfo = accountService.getAccountInfoById(1);

        System.out.println(accountInfo);
    }

}
