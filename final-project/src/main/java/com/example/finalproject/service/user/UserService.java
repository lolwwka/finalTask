package com.example.finalproject.service.user;

import com.example.finalproject.dto.UserDto;
import com.example.finalproject.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {
    List<User> getUsers();

    boolean addUser(UserDto userDto);
//    User getUserByEmail(String email);
}
