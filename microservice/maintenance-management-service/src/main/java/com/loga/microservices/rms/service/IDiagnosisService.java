package com.loga.microservices.rms.service;

import com.loga.microservices.rms.entity.Diagnosis;

public interface IDiagnosisService {

    Diagnosis create(Diagnosis diagnosis);

    Diagnosis read(Long id);
}
