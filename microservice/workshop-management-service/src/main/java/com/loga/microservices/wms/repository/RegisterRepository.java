package com.loga.microservices.wms.repository;

import com.loga.microservices.wms.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface RegisterRepository extends JpaRepository<Register,Long> {
    Register findByCreatedAt(Date date);
    List<Register> findAllByCreatedAtBetween(Date debut, Date fin);
}
