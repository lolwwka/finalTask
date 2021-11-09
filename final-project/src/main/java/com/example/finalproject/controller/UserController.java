package com.example.finalproject.controller;

import com.example.finalproject.dto.Result;
import com.example.finalproject.dto.UserDto;
import com.example.finalproject.dto.UserProfileDto;
import com.example.finalproject.service.user.UserService;
import org.springframework.web.bind.annotation.*;


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
    @PutMapping
    public Result changeUserPassword(@RequestBody UserDto userDto){
        if(userDto.getPassword() == null) {
            return new Result(userService.sendUserCodeChangePassword(userDto.getEmail()));
        }
        else {
            return new Result(userService.changeUserPassword(userDto.getEmail(), userDto.getPassword()));
        }
    }
}
