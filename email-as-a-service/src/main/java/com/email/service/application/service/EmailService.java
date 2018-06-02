package com.email.service.application.service;

import java.util.Collection;

import com.email.service.application.request.EmailRequest;
import com.email.service.application.response.EmailResponse;
import com.email.service.application.response.EmailSend;

import brave.ScopedSpan;

public interface EmailService {

	public EmailResponse sendEmail(EmailRequest emailRequest, ScopedSpan span);
	public Collection<EmailSend> getEmailDetails(ScopedSpan span);

}
