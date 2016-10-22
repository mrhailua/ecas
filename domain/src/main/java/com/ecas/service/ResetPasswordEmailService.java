package com.ecas.service;

import com.ecas.domain.User;

public interface ResetPasswordEmailService {
    void sendResetEmail(User user);
}
