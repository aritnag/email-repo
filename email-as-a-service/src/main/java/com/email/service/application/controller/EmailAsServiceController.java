package com.email.service.application.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.email.service.application.constants.EmailConstants;
import com.email.service.application.exception.EmailServiceException;
import com.email.service.application.exception.ErrorResponseVO;
import com.email.service.application.request.EmailRequest;
import com.email.service.application.response.EmailResponse;
import com.email.service.application.response.EmailSend;
import com.email.service.application.service.EmailService;

import brave.ScopedSpan;
import brave.Tracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="emailAPI")
@RequestMapping(value = { EmailConstants.REQUEST_MAPPING })
public class EmailAsServiceController {

	@Autowired
	private Tracer tracer;
	
	private static final Logger LOG = LoggerFactory.getLogger(EmailAsServiceController.class);

	@Autowired
	private EmailService emailService;

	@ApiOperation(value = "This API send Email and save the details in the Database", response = EmailResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Email is Send and Details is Saved", response = EmailResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponseVO.class) })
	@RequestMapping(value = { EmailConstants.SEND_EMAIL }, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<EmailResponse> sendEmail(@RequestBody EmailRequest emailRequest)
			throws EmailServiceException {
		final ScopedSpan span = this.tracer.startScopedSpan("Calling Send Email Method");
		EmailResponse res = emailService.sendEmail(emailRequest, span);
		LOG.info("Email Response"+res);
		this.tracer.currentSpan().finish();
		if (res.getStatus().equalsIgnoreCase(EmailConstants.ERROR_STATUS)) {
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		} else if (res.getStatus().equalsIgnoreCase(EmailConstants.SUCCESS)) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "This API gets the list of Total Email Send", response = EmailSend.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List of Email Send", response = EmailSend.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponseVO.class) })
	@RequestMapping(value = { EmailConstants.LIST_EMAIL_SEND }, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Collection<EmailSend>> getEmailList(HttpServletRequest request,
			HttpServletResponse response)
			throws EmailServiceException {
		final ScopedSpan span = this.tracer.startScopedSpan("Calling List Email Send Method");
		Collection<EmailSend	> res = emailService.getEmailDetails(span);
		LOG.info("Email Response"+res);
		this.tracer.currentSpan().finish();
		if (CollectionUtils.isEmpty(res)) {
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		} else if (!CollectionUtils.isEmpty(res)) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
