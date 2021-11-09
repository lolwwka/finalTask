package com.example.finalproject.service.email;

import javax.mail.MessagingException;

public interface CustomEmailService {
    boolean sendCodeEmail(String email, String code, int option) throws MessagingException;
}
