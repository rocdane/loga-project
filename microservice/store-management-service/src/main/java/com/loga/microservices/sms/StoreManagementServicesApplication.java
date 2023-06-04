package com.loga.microservices.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StoreManagementServicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(StoreManagementServicesApplication.class, args);
	}
}
