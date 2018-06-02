package com.email.service.application.test.cases;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.email.service.application.BaseAPITest;
import com.email.service.application.request.EmailRequest;
import com.email.service.application.response.EmailResponse;

public class EmailServiceApplicationTest extends BaseAPITest {
	private static final String EMAIL_URL_SEND = "/email/send/mail";
	private static final String EMAIL_URL_LIST = "/email/send/email/list";
	
	@Test
	public void testSendAndSaveEmail() throws Exception {
		String requestJSON = fetchJSONFileContentAsString(DATA_FOLDER + "email-send-request.json");
		HttpEntity<EmailRequest> entity = new HttpEntity<EmailRequest>(gson.fromJson(requestJSON, EmailRequest.class),getDefaultHeaders());
		ResponseEntity<EmailResponse> response = restTemplate().exchange(createURLWithPort(EMAIL_URL_SEND), HttpMethod.POST,
				entity, EmailResponse.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}
	
	
	private HttpHeaders getDefaultHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.add("Accept", "application/json");
		httpHeaders.add("Content-Type", "application/json");
		return httpHeaders;
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
