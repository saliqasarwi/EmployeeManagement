package com.sali.EmployeeManagement.repository;

import com.sali.EmployeeManagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email); // Must return Optional<Employee>
}