package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
public class SpringMainApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringMainApp.class,args);
	}

	@RestController
	public class Demo{
		@GetMapping("/")
		public String getHome(){
			return "this works";
		}
	}
}


