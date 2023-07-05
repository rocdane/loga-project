package com.loga.intelligentservice.service;

import com.loga.intelligentservice.app.api.JenaAPI;
import com.loga.intelligentservice.app.factory.Diagnosis;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntelligenceService implements IIntelligenceService {

    @Override
    public List<Diagnosis> resolve(String words) {
        return JenaAPI.getInstance().query(words);
    }
}
