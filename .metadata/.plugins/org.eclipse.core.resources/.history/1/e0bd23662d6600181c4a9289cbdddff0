package com.email.service.application.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.email.service.application.constants.EmailConstants;
import com.email.service.application.controller.EmailAsServiceController;
import com.email.service.application.dao.EmailDAO;
import com.email.service.application.entity.EmailDetails;
import com.email.service.application.exception.EmailServiceException;
import com.email.service.application.request.EmailRequest;
import com.email.service.application.response.EmailResponse;
import com.email.service.application.response.EmailSend;
import com.email.service.application.service.EmailService;
import com.email.support.application.request.SmtpEmail;
import com.email.support.application.service.SmtpHandler;

import brave.ScopedSpan;

@Service
public class EmailServiceImpl implements EmailService {

	private static final Logger LOG = LoggerFactory.getLogger(EmailAsServiceController.class);

	@Autowired
	private EmailDAO emailDAO;
	
	@Autowired
	private Environment env;

	@Autowired
	private SmtpHandler smtpHandler;

	@Override
	public EmailResponse sendEmail(EmailRequest emailRequest, ScopedSpan span) {
		EmailResponse response = null;
		try {
			LOG.debug("Email Request" + emailRequest);
			response = new EmailResponse();
			if (validateRequest(emailRequest)) {
				 SmtpEmail smtpEmail = populateEmailRequest(emailRequest);
			        smtpHandler.post(smtpEmail);
				EmailDetails emailDetails = emailDAO.saveEmailDetails(emailRequest, span);

				if (null != emailDetails) {
					LOG.info("Email Saved Details" + emailDetails);
					response = sucessResponseFormation(emailDetails, response,span);
				} else {
					LOG.info("Error in Saving Details");
					response = errorResponseFormation(emailRequest,response,span);
					throw new EmailServiceException("Exception in Sending Email", span);
				}
			} else {
				response = errorResponseFormation(emailRequest,response,span);
			}

		} catch (Exception e) {
			LOG.info("Exception in Send Email");
			LOG.info(e.getMessage());
		}
		return response;
	}

	private SmtpEmail populateEmailRequest(EmailRequest emailRequest) {
		SmtpEmail smtpEmail = new SmtpEmail();
		smtpEmail.setEmailAddress(new String[] {emailRequest.getEmailAddress()});
		smtpEmail.setContent(emailRequest.getContent());
		smtpEmail.setSubject(emailRequest.getSubject());
		smtpEmail.setPassword(env.getProperty(EmailConstants.PASSWORD));
		smtpEmail.setUsername(env.getProperty(EmailConstants.USERNAME));
		return smtpEmail;
	}

	private boolean validateRequest(EmailRequest emailRequest) {
		if (StringUtils.isEmpty(emailRequest.getSubject())) {
			LOG.info("Subject Cannot be null");
			return false;
		} else if (emailRequest.getContent().length() > 65000) {
			LOG.info("Content is too big");
			return false;
		}
		return true;
	}

	private EmailResponse sucessResponseFormation(EmailDetails emailDetails, EmailResponse response, ScopedSpan span) {
		EmailResponse res = new EmailResponse();
		res.setStatus(EmailConstants.SUCCESS);
		res.setTraceId(span.context().traceIdString());
		res.setUniqueEmailIdentifier(String.valueOf(emailDetails.getId()));
		return res;
	}

	private EmailResponse errorResponseFormation(EmailRequest request, EmailResponse response,ScopedSpan span) {
		EmailResponse res = new EmailResponse();
		res.setStatus(EmailConstants.ERROR_STATUS);
		res.setTraceId(span.context().traceIdString());
		res.setUniqueEmailIdentifier(request.toString());
		return res;
	}

	@Override
	public Collection<EmailSend> getEmailDetails(ScopedSpan span) {
		List<EmailDetails> emailDetailsList=emailDAO.getEmails(span);
		Collection<EmailSend> response = emailDetailsList.stream().map(temp -> {
			EmailSend obj=new EmailSend();
			BeanUtils.copyProperties(temp, obj);
			return obj;
		}).collect(Collectors.toList());
		return response;
	}
}
