package com.mindtree.productservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableDiscoveryClient
@EnableCaching
@SpringBootApplication
public class YoyoProductserviceApplication {
	
	private static Logger logger=LogManager.getLogger(YoyoProductserviceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(YoyoProductserviceApplication.class, args);
	logger.info("----> Product Service Application just started");
	}

}

