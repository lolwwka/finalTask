package com.example.finalproject.service;

import javax.mail.MessagingException;

public interface CustomEmailService {
    boolean sendCodeEmail(String email, String code) throws MessagingException;
}
