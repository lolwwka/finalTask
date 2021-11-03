package com.example.finalproject.service.user;

import com.example.finalproject.dto.UserDto;
import com.example.finalproject.entity.Role;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.RoleRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.email.CustomEmailService;
import com.example.finalproject.service.register.RegisterService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CustomEmailService emailService;
    private final RegisterService registerService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, CustomEmailService emailService, RegisterService registerService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.registerService = registerService;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean addUser(UserDto userDto) {
        User user = convertUser(userDto);
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new RuntimeException("User is already exists");
        }
        userRepository.save(user);
        new Thread(() -> sendCodeEmail(user)).start();
        return true;
    }

//    @Override
//    public User getUserByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }

    public boolean sendCodeEmail(User user) {
        try {
            return emailService.sendCodeEmail(user.getEmail(), user.getKeyCode());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    private User convertUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.getById(2L);
        HashSet<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setKeyCode(registerService.generateUserCode(user.getEmail()));
        return user;
    }
}
