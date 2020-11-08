package com.micro.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


//유레카 서버 선언 클라이언트들의 상태를 모니터링 할수 있음.
@SpringBootApplication
@EnableEurekaServer
public class SpirngCloudEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpirngCloudEurekaApplication.class, args);
	}

}
