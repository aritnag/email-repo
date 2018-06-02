package com.email.service.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.email.support.application.service.SmtpHandler;

@Configuration
public class EmailConfiguration {
	
	@Bean(name="smtpHandler")
    public SmtpHandler smtpHandler() {
        return new SmtpHandler();
    }

}
