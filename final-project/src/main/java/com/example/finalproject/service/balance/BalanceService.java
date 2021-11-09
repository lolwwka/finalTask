package com.example.finalproject.service.balance;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BalanceService {
    boolean addFreeMoney(String login);
}
