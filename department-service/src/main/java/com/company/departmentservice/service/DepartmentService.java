package com.company.departmentservice.service;

import com.company.departmentservice.dto.DepartmentDTO;
import com.company.departmentservice.exception.DepartmentException;

public interface DepartmentService {

    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) throws DepartmentException;

    DepartmentDTO getDepartmentCode(String departmentCode) throws DepartmentException;
}
