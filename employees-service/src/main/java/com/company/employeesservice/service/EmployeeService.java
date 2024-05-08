package com.company.employeesservice.service;

import com.company.employeesservice.dto.APIResponseDTO;
import com.company.employeesservice.dto.EmployeeDTO;
import com.company.employeesservice.entity.Employee;
import com.company.employeesservice.exception.EmployeeException;
import reactor.core.publisher.Mono;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws EmployeeException;

//    we changed here as well from Employee DTO to ApiResponseDto
    APIResponseDTO getEmployeeById(Long empId) throws EmployeeException;

    Mono<APIResponseDTO> findByEmployeeAndDepartmentId(Long empId, Long deptId) throws EmployeeException;

    EmployeeDTO updateEmployee(Long empId) throws EmployeeException;

    void deleteEmployeeByEmpId(Long empId) throws EmployeeException;
}
