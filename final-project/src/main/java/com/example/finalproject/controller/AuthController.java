package com.example.finalproject.controller;

import com.example.finalproject.dto.UserDto;
import com.example.finalproject.entity.User;
import com.example.finalproject.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/authentication")
public class AuthController {
    private final UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto authenticate(Principal principal) {
        LOGGER.debug("User {} logged in", principal.getName());
        UserDto userDto = new UserDto();
        User user = userService.getUserByEmail(principal.getName());
        userDto.setEmail(principal.getName());
        userDto.setRoles(userService.convertUserRoles(user.getRoles()));
        userDto.setId(user.getId());
        return userDto;
    }
}
