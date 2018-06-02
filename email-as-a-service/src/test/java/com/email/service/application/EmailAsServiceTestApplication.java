package com.email.service.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = { "com.email.service", "com.email.support" })
@PropertySource({ "classpath:application.properties" })
public class EmailAsServiceTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailAsServiceTestApplication.class, args);
	}
}