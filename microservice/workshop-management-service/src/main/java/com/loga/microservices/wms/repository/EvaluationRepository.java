package com.loga.microservices.wms.repository;

import com.loga.microservices.wms.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {
}
