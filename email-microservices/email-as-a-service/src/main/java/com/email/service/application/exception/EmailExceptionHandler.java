package com.email.service.application.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.email.service.application.constants.EmailConstants;

import brave.Tracer;

@ControllerAdvice
public class EmailExceptionHandler {
	
	@Autowired
	private Tracer tracer;
	private static final Logger LOG = LoggerFactory.getLogger(EmailExceptionHandler.class);
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	final ErrorResponseVO handleUnhandledException(HttpServletRequest req, Exception ex) {
		LOG.error("Unhandled exception ", ex);
		ErrorResponseVO errorResponseVO=new ErrorResponseVO();
		errorResponseVO.setErrorMessage("Exception in Sending Email");
		errorResponseVO.setTraceId(tracer.currentSpan().context().traceIdString());
		errorResponseVO.setErrorCode("ERR_01");
		errorResponseVO.setStatus(EmailConstants.ERROR_STATUS);
		return errorResponseVO;
	}
}
