package com.mindtree.cartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableResourceServer
public class YoyoCartserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoyoCartserviceApplication.class, args);
	}

}
