package com.loga.microservices.sms.repository;

import com.loga.microservices.sms.entity.Furnisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnisherRepository extends JpaRepository<Furnisher,Long> {
}
