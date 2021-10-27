package com.example.finalproject.service;

import com.example.finalproject.dao.UserRepository;
import com.example.finalproject.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RegisterServiceImpl implements RegisterService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean checkCode(String code) {
        User user = userRepository.findByKeyCode(code);
        if(user != null){
            user.setAuthenticated(true);
            user.setKeyCode(null);
            userRepository.flush();
            return true;
        }
        return false;
    }

    @Override
    public String generateUserCode(String email) {
        StringBuilder keyCode = new StringBuilder(passwordEncoder.encode(email));
        User user = userRepository.findByKeyCode(keyCode.toString());
        if(user!= null) generateUserCode(email);
        char[] chars = keyCode.toString().toCharArray();
        keyCode = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '/') chars[i] = '0';
            keyCode.append(chars[i]);
        }
        return keyCode.toString();
    }
}
