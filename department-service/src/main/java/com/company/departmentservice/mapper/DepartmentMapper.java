package com.company.departmentservice.mapper;

import com.company.departmentservice.dto.DepartmentDTO;
import com.company.departmentservice.entity.Department;

public class DepartmentMapper {

//    convert Department JPA to DepartmentDTO

    public static DepartmentDTO mapToDepartmentDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO(
                department.getDeptId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDTO;
    }

    //convert DepartmentDTO to Department JPA

    public static Department mapToDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department(
                departmentDTO.getDeptId(),
                departmentDTO.getDepartmentName(),
                departmentDTO.getDepartmentDescription(),
                departmentDTO.getDepartmentCode()
        );
        return department;
    }
}


