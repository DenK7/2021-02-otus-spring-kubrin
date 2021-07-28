package ru.otus.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
public class IntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrationApplication.class, args);
	}

}
