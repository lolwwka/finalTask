package com.example.finalproject.service.register;

public interface RegisterService {
    boolean checkCode(String code);

    String generateUserCode(String email);
}
