package ru.iteco.itecospringcource.service;

import ru.iteco.itecospringcource.model.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();
    UserDto getById(Integer id);
    UserDto create(UserDto user);
    UserDto update(UserDto user);
    void delete(Integer id);

}
