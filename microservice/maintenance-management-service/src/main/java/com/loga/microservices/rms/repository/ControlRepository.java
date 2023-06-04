package com.loga.microservices.rms.repository;

import com.loga.microservices.rms.entity.Control;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ControlRepository extends JpaRepository<Control,Long> {
}
