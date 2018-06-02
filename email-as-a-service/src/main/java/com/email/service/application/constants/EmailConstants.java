package com.email.service.application.constants;

import org.springframework.stereotype.Component;

@Component
public interface EmailConstants {
	public final static String REQUEST_MAPPING = "/email";
	public static final String SEND_EMAIL = "/send/mail";
	public static final String ERROR_STATUS = "E";
	public static final String SUCCESS = "S";
	public static final String PASSWORD = "email.password";
	public static final String USERNAME = "email.username";
	public static final String LIST_EMAIL_SEND = "/send/email/list";
}
