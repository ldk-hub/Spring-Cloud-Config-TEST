package com.ldk.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


//@EnableConfigServer은 Git Repository의 설정한 yml의 정보를 읽어온다.
@SpringBootApplication
@EnableConfigServer
public class SpirngCloudServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpirngCloudServerApplication.class, args);
	}

}
