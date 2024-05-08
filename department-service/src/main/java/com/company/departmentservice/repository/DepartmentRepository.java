package com.company.departmentservice.repository;

import com.company.departmentservice.dto.DepartmentDTO;
import com.company.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentCode(String departmentCode);

    //Department saveDepartment(Department department);

    //void deleteByDepartmentCode(String departmentCode);

}