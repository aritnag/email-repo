package com.email.service.application.exception;

import io.swagger.annotations.ApiModelProperty;

public class ErrorResponseVO {

	private String status = "E";

	@ApiModelProperty(value = "The is the error code returned from any business validation or the system errors.")
	private String errorCode;

	@ApiModelProperty(value = "Valid error message from the backend")
	private String errorMessage;

	private String traceId;

	/**
	 * 
	 * @return the status
	 * 
	 */

	public String getStatus() {

		return this.status;

	}

	/**
	 * 
	 * @param status
	 * 
	 *            the status to set
	 * 
	 */

	public void setStatus(String status) {

		this.status = status;

	}

	/**
	 * 
	 * @return the errorCode
	 * 
	 */

	public String getErrorCode() {

		return errorCode;

	}

	/**
	 * 
	 * @param errorCode
	 * 
	 *            the errorCode to set
	 * 
	 */

	public void setErrorCode(String errorCode) {

		this.errorCode = errorCode;

	}

	/**
	 * 
	 * @return the errorMessage
	 * 
	 */

	public String getErrorMessage() {

		return this.errorMessage;

	}

	/**
	 * 
	 * @param errorMessage
	 * 
	 *            the errorMessage to set
	 * 
	 */

	public void setErrorMessage(String errorMessage) {

		this.errorMessage = errorMessage;

	}

	public String getTraceId() {

		return this.traceId;

	}

	public void setTraceId(String traceId) {

		this.traceId = traceId;

	}

	@Override
	public String toString() {

		return "ErrorResponseVO [status=" + this.status + ", errorCode=" + this.errorCode + ", errorMessage="

				+ this.errorMessage + "]";

	}

}
