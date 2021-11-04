package com.example.finalproject.service.money;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BalanceService {
    boolean addFreeMoney(String login);
}
