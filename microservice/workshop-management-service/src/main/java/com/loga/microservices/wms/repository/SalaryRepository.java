package com.loga.microservices.wms.repository;

import com.loga.microservices.wms.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long> {
}
