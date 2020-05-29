package com.cache.exampleissue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ExampleIssue25124Application {

	public static void main(String[] args) {
		SpringApplication.run(ExampleIssue25124Application.class, args);
	}

}
