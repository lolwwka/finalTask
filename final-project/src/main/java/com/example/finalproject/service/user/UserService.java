package com.example.finalproject.service.user;

import com.example.finalproject.dto.UserDto;
import com.example.finalproject.dto.UserProfileDto;
import com.example.finalproject.entity.Role;
import com.example.finalproject.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
public interface UserService {
    List<User> getUsers();

    boolean addUser(UserDto userDto);

    User getUserByEmail(String email);

    UserProfileDto getUserInfo(String login);

    Set<String> convertUserRoles(Set<Role> roles);

    boolean sendUserCodeChangePassword(String email);

    boolean changeUserPassword(String email, String password);
}
