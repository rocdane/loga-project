package com.loga.microservices.wms.repository;

import com.loga.microservices.wms.entity.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaRepository extends JpaRepository<Criteria,Long> {
}
