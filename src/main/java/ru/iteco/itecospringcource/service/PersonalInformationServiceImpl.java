package ru.iteco.itecospringcource.service;

import org.springframework.stereotype.Service;
import ru.iteco.itecospringcource.model.PersonalInfo;

@Service
public class PersonalInformationServiceImpl implements PersonalInformationService {

    @Override
    public PersonalInfo getPersonalInfoById(Integer id) {
        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setName("NAME");
        personalInfo.setUserId(id);

        return personalInfo;
    }

}
