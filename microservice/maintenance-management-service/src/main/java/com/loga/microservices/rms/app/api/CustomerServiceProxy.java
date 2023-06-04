package com.loga.microservices.rms.app.api;

import com.loga.microservices.rms.app.factory.Dossier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("gateway-server")
public interface CustomerServiceProxy {

    @GetMapping(path = "/customer-service/dossiers/reference/{reference}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Dossier retrieveDossier(@PathVariable String reference);

    @GetMapping(path = "/customer-service/dossiers/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Dossier retrieveDossier(@PathVariable Long id);

    @GetMapping(path = "/customer-service/dossiers",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dossier> retrieveDossier();
}
