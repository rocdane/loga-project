package com.loga.microservices.datamanagementservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DataManagementServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataManagementServicesApplication.class, args);
	}

}
