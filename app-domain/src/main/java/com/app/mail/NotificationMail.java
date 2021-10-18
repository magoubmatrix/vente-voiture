package com.app.mail;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationMail {
	
	private String subject;
	private String recipt;
	private String body;

}
