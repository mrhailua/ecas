package com.ecas.service;

import com.ecas.domain.User;

public interface ActiveProfileEmailService {
	void sendActivateEmail(User worker);
}
