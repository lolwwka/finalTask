package com.example.finalproject.controller;

import com.example.finalproject.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/authentication")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @PostMapping
    public UserDto authenticate(Principal principal) {
        LOGGER.debug("User {} logged in", principal.getName());
        UserDto user = new UserDto();
        user.setEmail(principal.getName());
        return user;
    }
}
