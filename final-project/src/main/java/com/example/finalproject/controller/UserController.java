package com.example.finalproject.controller;

import com.example.finalproject.dto.UserProfileDto;
import com.example.finalproject.service.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{login}")
    public UserProfileDto getUserInfo(@PathVariable String login) {
        return userService.getUserInfo(login);
    }
}
