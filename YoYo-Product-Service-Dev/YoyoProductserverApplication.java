package com.mindtree.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class YoyoProductserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoyoProductserverApplication.class, args);
	}

}
