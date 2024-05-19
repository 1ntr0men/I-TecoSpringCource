package ru.iteco.itecospringcource.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iteco.itecospringcource.model.UserAuthDto;
import ru.iteco.itecospringcource.service.UserAuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAuthService userAuthService;

    public AuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @PostMapping("/create")
    public String create(@RequestBody UserAuthDto userAuthDto) {
        return userAuthService.create(userAuthDto);
    }

}
