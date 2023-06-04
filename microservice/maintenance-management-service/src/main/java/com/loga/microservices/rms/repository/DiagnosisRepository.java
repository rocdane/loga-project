package com.loga.microservices.rms.repository;

import com.loga.microservices.rms.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis,Long> {

    Diagnosis findByReference(String reference);
}
