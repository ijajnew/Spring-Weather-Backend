package com.etravali.assignment.notification;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class EmailNotificatoin implements NotificationService {

	@Override
	public String getType() {
		return "email";
	}

	@Override
	public String sendNotification() {
		return "Email notification has been sent";
	}

}
