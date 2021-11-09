package com.example.finalproject.service.register;

import com.example.finalproject.dto.Result;

public interface RegisterService {
    Result checkCode(String code);

    String generateUserCode(String email);
}
