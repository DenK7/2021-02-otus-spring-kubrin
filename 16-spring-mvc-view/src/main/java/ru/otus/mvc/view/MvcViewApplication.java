package ru.otus.mvc.view;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongock
@EnableMongoRepositories(basePackages = "ru.otus.mvc.view.repositories")
public class MvcViewApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcViewApplication.class, args);
	}

}
