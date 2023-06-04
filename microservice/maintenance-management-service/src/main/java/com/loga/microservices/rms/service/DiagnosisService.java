package com.loga.microservices.rms.service;

import com.loga.microservices.rms.entity.Diagnosis;
import com.loga.microservices.rms.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class DiagnosisService implements IDiagnosisService {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Diagnostic dans la base de données.
     * @param diagnosis
     * @return Diagnosis
     */
    @Override
    @Transactional
    public Diagnosis create(Diagnosis diagnosis) {
        diagnosis.setCreatedAt(new Date());
        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public Diagnosis read(Long id) {
        return diagnosisRepository
                .findById(id)
                .get();
    }
}
