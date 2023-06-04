package com.loga.reportservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ReportService {

	public static void main(String[] args) {
		SpringApplication.run(ReportService.class, args);
	}

}
