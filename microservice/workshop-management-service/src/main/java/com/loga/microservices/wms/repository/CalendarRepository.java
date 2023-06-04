package com.loga.microservices.wms.repository;

import com.loga.microservices.wms.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<Schedule,Long> {
}
