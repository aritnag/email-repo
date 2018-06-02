package com.email.service.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmailAsServiceTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseAPITest {

	@LocalServerPort
	protected int port;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	protected static final String DATA_FOLDER = "data/";
	protected Gson gson = new Gson();

	protected HashMap<String, Object> fetchJSON(JSONType jsonType, String payload) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
			};
			if (jsonType.equals(JSONType.FILE)) {
				return objectMapper.readValue(
						new File(Thread.currentThread().getContextClassLoader().getResource(payload).getFile()),
						typeRef);
			} else if (jsonType.equals(JSONType.STRING)) {
				return objectMapper.readValue(payload, typeRef);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected String fetchJSONFileContentAsString(String fileName) {
		BufferedReader jsonBufferedReader = null;
		try {
			jsonBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(
					new File(Thread.currentThread().getContextClassLoader().getResource(fileName).getFile()))));
			String line = jsonBufferedReader.readLine();
			StringBuilder sb = new StringBuilder();
			while (line != null) {
				sb.append(line).append("\n");
				line = jsonBufferedReader.readLine();
			}
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (jsonBufferedReader != null) {
				try {
					jsonBufferedReader.close();
				} catch (IOException e) {
					// ignore.
				}
			}
		}
		return null;
	}
}
