package com.loga.distributedtracing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
@EnableDiscoveryClient
public class DistributedTracingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedTracingApplication.class, args);
	}

}
