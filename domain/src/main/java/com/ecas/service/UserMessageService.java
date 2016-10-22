package com.ecas.service;

import java.util.List;

import com.ecas.domain.User;
import com.ecas.domain.UserMessage;

/**
 * Handle user message data
 * 
 * @author LENOVO
 *
 */
public interface UserMessageService extends GenericDataService<UserMessage> {
	List<UserMessage> getMessageByUser(Integer userId);

	Long getMessageCount(Integer userId);

	void markRead(Integer messageId);
	
	void cleanUp(User user, User systemAccount);
}
