package com.example.finalproject.service;

public interface RegisterService {
    boolean checkCode(String code);

    String generateUserCode(String email);
}
