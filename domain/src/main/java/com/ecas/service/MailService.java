package com.ecas.service;


public interface MailService {
    void sendMail(String to, String subject, String msg);
}
