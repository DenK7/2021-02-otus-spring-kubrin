package ru.otus.base.spring.security;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongock
@EnableMongoRepositories(basePackages = "ru.otus.base.spring.security.repositories")
public class BaseSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseSpringSecurityApplication.class, args);
	}

}
