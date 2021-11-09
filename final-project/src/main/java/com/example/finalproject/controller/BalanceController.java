package com.example.finalproject.controller;

import com.example.finalproject.dto.Result;
import com.example.finalproject.service.balance.BalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    private final BalanceService balanceService;
    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceController.class);


    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @PostMapping
    public Result getFreeMoney(Principal principal) {
        LOGGER.info("User {} taking free money", principal.getName());
        return new Result(balanceService.addFreeMoney(principal.getName()));
    }
}
