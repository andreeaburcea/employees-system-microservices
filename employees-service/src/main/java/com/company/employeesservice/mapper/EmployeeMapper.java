package com.company.employeesservice.mapper;

import com.company.employeesservice.dto.EmployeeDTO;
import com.company.employeesservice.entity.Employee;
import org.springframework.stereotype.Component;


public class EmployeeMapper {

    //convert JPA to DTO
    public static EmployeeDTO mapToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getEmpId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getHireDate(),
                employee.getDepartmentCode(),
                employee.getDeptId(),
                employee.getOrganizationCode()
        );
        return employeeDTO;
    }

    // convery DTO to JPA

    public static Employee mapToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getEmpId(),
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getEmail(),
                employeeDTO.getPhoneNumber(),
                employeeDTO.getHireDate(),
                employeeDTO.getDepartmentCode(),
                employeeDTO.getDeptId(),
                employeeDTO.getOrganizationCode()
        );

        return  employee;
    }

}




