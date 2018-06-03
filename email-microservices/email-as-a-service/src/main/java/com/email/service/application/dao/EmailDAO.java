package com.email.service.application.dao;

import java.util.List;

import com.email.service.application.entity.EmailDetails;
import com.email.service.application.request.EmailRequest;

import brave.ScopedSpan;

public interface EmailDAO {

	public EmailDetails saveEmailDetails(EmailRequest emailRequest, ScopedSpan span);
	public List<EmailDetails> getEmails(ScopedSpan span);

}
