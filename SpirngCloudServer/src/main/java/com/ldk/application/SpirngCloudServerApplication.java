package com.ldk.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableEurekaClient
@RestController
public class SpirngCloudServerApplication {
	 

	
	public static void main(String[] args) {
		SpringApplication.run(SpirngCloudServerApplication.class, args);
	}

	 	@Bean
	    @LoadBalanced //리본으로 트래픽 라운드로빈으로 분배 1번클라 2번클라 테스트해야됨.
	    //1은 h2db connect 2는 postgresql로 설정
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }

	 	@Autowired
	     RestTemplate restTemplate;
	 	
	 	
	@RequestMapping(value="/")
	public String hoem() {
		return "Eureka Client Application Test1";
	}
}
