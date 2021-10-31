package com.example.finalproject.service;

import com.example.finalproject.dto.UserDto;
import com.example.finalproject.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {
    List<User> getUsers();

    boolean addUser(UserDto userDto);

    boolean sendCodeEmail(User user);
}
