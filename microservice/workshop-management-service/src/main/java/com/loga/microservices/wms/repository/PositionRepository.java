package com.loga.microservices.wms.repository;

import com.loga.microservices.wms.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {
    Position findByDesignation(String designation);
}
