package com.company.employeesservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * Data Transfer Object (DTO) representing the API response structure.
 * This class encapsulates the response data returned by the employee service API,
 * including information about both employees, departments and organizations.
 */
public class APIResponseDTO {
    private EmployeeDTO employeeDTO;
    private DepartmentDTO departmentDTO;
    private OrganizationDTO organizationDTO;
}
