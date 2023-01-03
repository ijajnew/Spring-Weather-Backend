package com.etravali.assignment.notification;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SlackNotification implements NotificationService {

	@Override
	public String getType() {
		return "slack";
	}

	@Override
	public String sendNotification() {
		return "Slack Notification has been sent";
	}

}
