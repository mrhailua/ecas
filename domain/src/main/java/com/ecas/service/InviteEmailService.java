package com.ecas.service;

import com.ecas.domain.User;

public interface InviteEmailService {
	void sendInviteEmail(User requestUser, User inviteUser);
}
