package com.company.employeesservice.repository;

import com.company.employeesservice.dto.APIResponseDTO;
import com.company.employeesservice.dto.EmployeeDTO;
import com.company.employeesservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Mono;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

   // APIResponseDTO getEmployeeById(Long empId);

    //Mono<APIResponseDTO> findByEmployeeAndDepartmentId(Long empId, Long deptId);

    //Employee saveEmployee(Employee employee);

    //Employee updateEmployee(Long empId);

    //void deleteEmployeeByEmpId(Long empId);
}
