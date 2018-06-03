package com.email.service.application.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.email.service.application.dao.EmailDAO;
import com.email.service.application.entity.EmailDetails;
import com.email.service.application.request.EmailRequest;
import com.email.service.application.service.repository.EmailRepository;

import brave.ScopedSpan;

@Service
public class EmailDAOImpl implements EmailDAO {
	
	@Autowired
	private EmailRepository emailRepo;
	
	@Override
	public EmailDetails saveEmailDetails(EmailRequest emailRequest, ScopedSpan span) {
		EmailDetails emailDetails = new EmailDetails();
		emailDetails.setBody(emailRequest.getContent());
		emailDetails.setEmailAddress(emailRequest.getEmailAddress());
		emailDetails.setFrom(emailRequest.getFrom());
		emailDetails.setSubject(emailRequest.getSubject());
		emailDetails = emailRepo.save(emailDetails);
		return emailDetails;
	}

	@Override
	public List<EmailDetails> getEmails(ScopedSpan span) {
		return emailRepo.findAll();
	}

}
