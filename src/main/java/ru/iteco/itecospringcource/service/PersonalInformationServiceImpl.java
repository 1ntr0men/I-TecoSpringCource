package ru.iteco.itecospringcource.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.iteco.itecospringcource.model.PersonalInfo;

@Service
public class PersonalInformationServiceImpl implements PersonalInformationService {

    @Value("${name}")
    private String name;

    public PersonalInformationServiceImpl() {
    }

    @PostConstruct
    public void init() {
        if (name.contains("N")) {
            System.out.println("Contains 'N'");
        }
    }

    @Override
    public PersonalInfo getPersonalInfoById(Integer id) {
        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setName(name);
        personalInfo.setUserId(id);

        return personalInfo;
    }

}
