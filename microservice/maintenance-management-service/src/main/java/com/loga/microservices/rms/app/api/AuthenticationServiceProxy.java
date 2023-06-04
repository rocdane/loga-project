package com.loga.microservices.rms.app.api;

import com.loga.microservices.rms.app.factory.AuthSession;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("gateway-server")
public interface AuthenticationServiceProxy {
    @GetMapping(path = "/authentication-service/session/{token}",produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthSession fetchSession(@PathVariable String token);
}
