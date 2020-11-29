package com.micro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@EnableEurekaClient
@RestController
@SpringBootApplication
public class SpringEurekaClientApplication {
	 
	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaClientApplication.class, args);
	}

	 	@Bean
	    @LoadBalanced
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }

	 	@Autowired
	     RestTemplate restTemplate;
	 	
	 	
	@RequestMapping(value="/")
	public String hoem() {
		return "/login";
	}
}
