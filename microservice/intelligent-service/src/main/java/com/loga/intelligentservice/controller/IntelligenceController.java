package com.loga.intelligentservice.controller;

import com.loga.intelligentservice.app.factory.Diagnosis;
import com.loga.intelligentservice.service.IIntelligenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/intelligent-service")
public class IntelligenceController {

    @Autowired
    private IIntelligenceService intelligenceService;

    @GetMapping(path = "/resolve/{words}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Diagnosis> resolve(@PathVariable String words){
        return intelligenceService.resolve(words);
    }
}
