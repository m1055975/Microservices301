package com.mindtree.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableResourceServer
public class YoyoOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoyoOrderServiceApplication.class, args);
	}

}
