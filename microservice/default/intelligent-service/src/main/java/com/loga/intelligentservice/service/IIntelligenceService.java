package com.loga.intelligentservice.service;

import java.util.List;

public interface IIntelligenceService {
    List<Diagnosis> resolve(String words);
}
