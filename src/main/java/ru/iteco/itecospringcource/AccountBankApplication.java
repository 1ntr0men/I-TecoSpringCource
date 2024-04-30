package ru.iteco.itecospringcource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import ru.iteco.itecospringcource.model.AccountInfo;
import ru.iteco.itecospringcource.repository.ExternalRepository;
import ru.iteco.itecospringcource.service.*;

@Slf4j
@ComponentScan
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
public class AccountBankApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AccountBankApplication.class);
        AccountService accountService = applicationContext.getBean(AccountService.class);
        System.out.println("PersonalInfoClass: " + accountService.getPersonalInfoClass());
        AccountInfo accountInfo = accountService.getAccountInfoById(1);
        System.out.println("PersonalInfoClass: " + accountService.getPersonalInfoClass());

        System.out.println(accountInfo);

        IObject objectValue = applicationContext.getBean(IObject.class);
        System.out.println("objectValue type" + objectValue.getClass());
        System.out.println("result info: " + objectValue.getInfo());

        System.out.println();
        System.out.println("-----------------------------------Вывод ДЗ-----------------------------------");
        System.out.println();

        Flow flow = applicationContext.getBean(Flow.class);

        flow.run(1);
        flow.run(2);
        flow.run(2);
        flow.run(3);
        flow.run(4);

        ExternalRepository externalRepository = applicationContext.getBean(ExternalRepository.class);
        log.info("Result of externalRepository: {}", externalRepository.getInfo());
    }

}
