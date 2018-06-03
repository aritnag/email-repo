package com.email.service.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class EmailAsAServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(EmailAsAServiceApplication.class, args);
	}
}
