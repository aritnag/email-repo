package com.email.service.application.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class EmailRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="Email Address")
	private String emailAddress;

	@ApiModelProperty(value="Subject")
	private String subject;

	@ApiModelProperty(value="Content")
	private String content;

	@ApiModelProperty(value="From")
	private String from;

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailRequest [emailAddress=" + emailAddress + ", subject=" + subject + ", content=" + content
				+ ", from=" + from + "]";
	}
	

	
}
