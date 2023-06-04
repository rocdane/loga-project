package com.loga.microservices.rms.app.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("gateway-server")
public interface IntelligentServiceProxy {
    @GetMapping(path = "/intelligent-service/resolve/{words}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Object> processDiagnosis(@PathVariable String words);
}
