package com.example.finalproject.controller;

import com.example.finalproject.dto.UserDto;
import com.example.finalproject.service.RegisterService;
import com.example.finalproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
    public Map<String, String> addUser(@Valid @RequestBody UserDto user) {
        LOGGER.debug("Adding new user[{}]", user);
        userService.addUser(user);
        HashMap<String, String> map = new HashMap<>();
        map.put("callback", "true");
        return map;
    }

    @PostMapping(value = "/{code}")
    public Map<String, Boolean> checkCode(@PathVariable(value = "code") String code) {
        Map<String, Boolean> results = new HashMap<>();
        if (registerService.checkCode(code)) {
            results.put("confirmed", true);
        } else results.put("confirmed", false);
        return results;
    }
}
