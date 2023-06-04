package com.loga.microservices.wms.repository;

import com.loga.microservices.wms.entity.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop,Long> {
}
