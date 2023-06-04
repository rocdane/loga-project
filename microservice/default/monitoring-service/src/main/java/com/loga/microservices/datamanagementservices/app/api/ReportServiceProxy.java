package com.loga.microservices.datamanagementservices.app.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

@FeignClient("gateway-server")
public interface ReportServiceProxy {
}
