package com.loga.microservices.rms.repository;

import com.loga.microservices.rms.entity.Factor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactorRepository extends JpaRepository<Factor,Long> {
}
