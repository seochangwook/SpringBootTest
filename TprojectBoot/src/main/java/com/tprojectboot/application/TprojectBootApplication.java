package com.tprojectboot.application;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
public class TprojectBootApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(TprojectBootApplication.class, args);
		
		System.out.println("start spring boot project and db setting complete...");
	}
}
