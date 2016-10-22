package com.ecas.web.admin.bean.user;

import com.ecas.domain.UserMessage;
import com.ecas.service.UserMessageService;
import com.ecas.web.admin.bean.BaseBean;
import com.ecas.web.admin.bean.MessageReloader;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class UserMessageBean extends BaseBean implements MessageReloader,Serializable {
	private static final long serialVersionUID = -5792786415040835543L;
	private List<UserMessage> messages;
	private Integer messageCount;

	@PostConstruct
	public void init() {
		loadMessages();
	}

	public void loadMessages() {
	    messages = getDataService().getMessageByUser(getCurrentUser().getId());
		messageCount = getDataService().getMessageCount(getCurrentUser().getId()).intValue();
    }
	
	public void markRead(ActionEvent actionEvent) {
		getDataService().markRead((Integer) getAttribute(actionEvent, "messageId"));
		loadMessages();
	}

	public List<UserMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<UserMessage> messages) {
		this.messages = messages;
	}

	private UserMessageService getDataService() {
		return (UserMessageService) getServiceRegistry().getService(UserMessage.class);
	}

	public Integer getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(Integer messageCount) {
		this.messageCount = messageCount;
	}

}
