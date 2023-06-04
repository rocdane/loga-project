package com.loga.microservices.wms.app.api;

import com.loga.microservices.wms.app.factory.AuthSession;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;

@FeignClient(name = "authentication-management-services",url = "/loga/v1/authenticate")
public interface AuthenticationManagementServices {
    @GetMapping(path = "/session/{token}",produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthSession checkSession(@PathVariable String token);
}
