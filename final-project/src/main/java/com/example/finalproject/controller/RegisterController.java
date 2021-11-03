package com.example.finalproject.controller;

import com.example.finalproject.dto.Result;
import com.example.finalproject.dto.UserDto;
import com.example.finalproject.service.register.RegisterService;
import com.example.finalproject.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
    private final UserService userService;
    private final RegisterService registerService;

    public RegisterController(UserService userService, RegisterService registerService) {
        this.userService = userService;
        this.registerService = registerService;
    }

    @PostMapping()
    public Result addUser(@Valid @RequestBody UserDto user) {
        LOGGER.debug("Adding new user[{}]", user);
        userService.addUser(user);
        Result result = new Result();
        result.setResult(true);
        return result;
    }

    @PostMapping(value = "/{code}")
    public Result checkCode(@PathVariable(value = "code") String code) {
        Result result = new Result();
        result.setResult(registerService.checkCode(code));
        return result;
    }
}
