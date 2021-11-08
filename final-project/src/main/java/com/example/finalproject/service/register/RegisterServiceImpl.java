package com.example.finalproject.service.register;

import com.example.finalproject.entity.User;
import com.example.finalproject.repository.VisitorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RegisterServiceImpl implements RegisterService {
    private final VisitorRepository visitorRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterServiceImpl(VisitorRepository visitorRepository, PasswordEncoder passwordEncoder) {
        this.visitorRepository = visitorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean checkCode(String code) {
        User user = visitorRepository.findByKeyCode(code);
        if (user != null) {
            user.setAuthenticated(true);
            user.setKeyCode(null);
            visitorRepository.flush();
            return true;
        }
        return false;
    }

    @Override
    public String generateUserCode(String email) {
        StringBuilder keyCode = new StringBuilder(passwordEncoder.encode(email));
        User user = visitorRepository.findByKeyCode(keyCode.toString());
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
