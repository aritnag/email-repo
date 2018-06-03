package com.email.support.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.email.support.application.request.SmtpEmail;

@Service
public class SmtpHandler {

	private static final Logger LOG = LoggerFactory.getLogger(SmtpHandler.class);

	public void post(SmtpEmail email) {
		LOG.info("Email posted: " + email);
	}
}
