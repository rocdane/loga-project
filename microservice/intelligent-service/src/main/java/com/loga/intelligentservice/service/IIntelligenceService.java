package com.loga.intelligentservice.service;

import com.loga.intelligentservice.app.factory.Diagnosis;

import java.util.List;

public interface IIntelligenceService {
    List<Diagnosis> resolve(String words);
}
