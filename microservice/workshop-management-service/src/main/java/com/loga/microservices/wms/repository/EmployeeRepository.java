package com.loga.microservices.wms.repository;

import com.loga.microservices.wms.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByNameOrSurname(String name, String surname);
    Employee findByContact(String contact);
}
