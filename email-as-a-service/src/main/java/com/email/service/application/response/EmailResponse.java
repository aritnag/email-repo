package com.email.service.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_NULL)
public class EmailResponse {
	
	@ApiModelProperty(value="This is status of the API")
	private String status;
	@ApiModelProperty(value="The unique identifier for tracing the request/response")
	private String traceId;
	@ApiModelProperty(value="The unique identifier for tracing the request/response")
	private String uniqueEmailIdentifier;
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the traceId
	 */
	public String getTraceId() {
		return traceId;
	}
	/**
	 * @param traceId the traceId to set
	 */
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	/**
	 * @return the uniqueEmailIdentifier
	 */
	public String getUniqueEmailIdentifier() {
		return uniqueEmailIdentifier;
	}
	/**
	 * @param uniqueEmailIdentifier the uniqueEmailIdentifier to set
	 */
	public void setUniqueEmailIdentifier(String uniqueEmailIdentifier) {
		this.uniqueEmailIdentifier = uniqueEmailIdentifier;
	}

	
}
