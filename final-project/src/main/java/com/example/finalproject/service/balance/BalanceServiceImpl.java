package com.example.finalproject.service.balance;

import com.example.finalproject.entity.User;
import com.example.finalproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService {
    private final UserRepository userRepository;

    public BalanceServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean addFreeMoney(String login) {
        User user = userRepository.findByEmail(login);
        user.setBalance(user.getBalance() + 100);
        return true;
    }
}
