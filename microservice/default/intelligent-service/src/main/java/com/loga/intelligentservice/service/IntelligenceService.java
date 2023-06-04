package com.loga.intelligentservice.service;

import com.loga.intelligentservice.app.ontology.JenaAPI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntelligenceService implements IIntelligenceService {

    @Override
    public List<Diagnosis> resolve(String words) {
        return JenaAPI.getInstance().query(words);
    }
}
