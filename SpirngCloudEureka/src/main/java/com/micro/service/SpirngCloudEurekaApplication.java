package com.micro.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


//����ī ���� ���� Ŭ���̾�Ʈ���� ���¸� ����͸� �Ҽ� ����.
@SpringBootApplication
@EnableEurekaServer
public class SpirngCloudEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpirngCloudEurekaApplication.class, args);
	}

}
