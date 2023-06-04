package com.loga.microservices.wms.repository;

import com.loga.microservices.wms.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findByDesignation(String txt);
}
