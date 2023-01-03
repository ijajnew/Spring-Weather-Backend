package com.etravali.assignment.notification;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MessageNotification implements NotificationService {

	@Override
	public String getType() {
		return "message";
	}

	@Override
	public String sendNotification() {
		return "MessageNotification has been sent";
	}

}
