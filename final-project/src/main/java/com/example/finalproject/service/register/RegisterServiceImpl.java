package com.example.finalproject.service.register;

import com.example.finalproject.dto.Result;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RegisterServiceImpl implements RegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Result checkCode(String code) {
        Result result = new Result();
        User user = userRepository.findByKeyCode(code);
        if (user != null) {
            user.setAuthenticated(true);
            user.setKeyCode(null);
            result.setResult(true);
            result.setUserLogin(user.getEmail());
            userRepository.flush();
            return result;
        }
        result.setResult(false);
        return result;
    }

    @Override
    public String generateUserCode(String email) {
        StringBuilder keyCode = new StringBuilder(passwordEncoder.encode(email));
        User user = userRepository.findByKeyCode(keyCode.toString());
        if (user != null) generateUserCode(email);
        char[] chars = keyCode.toString().toCharArray();
        keyCode = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '/') chars[i] = '0';
            keyCode.append(chars[i]);
        }
        return keyCode.toString();
    }
}
