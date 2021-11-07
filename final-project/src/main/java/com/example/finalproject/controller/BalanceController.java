package com.example.finalproject.controller;

import com.example.finalproject.dto.Result;
import com.example.finalproject.service.money.BalanceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @PostMapping
    public Result getFreeMoney(Principal principal){
        return new Result(balanceService.addFreeMoney(principal.getName()));
    }
}
