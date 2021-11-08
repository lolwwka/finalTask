package com.example.finalproject.service.money;

import com.example.finalproject.entity.User;
import com.example.finalproject.repository.VisitorRepository;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService {
    private final VisitorRepository visitorRepository;

    public BalanceServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @Override
    public boolean addFreeMoney(String login) {
        User user = visitorRepository.findByEmail(login);
        user.setBalance(user.getBalance() + 100);
        return true;
    }
}
