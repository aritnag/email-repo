package com.email.support.application.request;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class SmtpEmail {

    private String username;
    private String password;
    private String [] emailAddress;
    private String subject;
    private String content;
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String[] emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SmtpEmail [username=" + username + ", password=" + password + ", emailAddress="
				+ Arrays.toString(emailAddress) + ", subject=" + subject + ", content=" + content + "]";
	}
   
}
