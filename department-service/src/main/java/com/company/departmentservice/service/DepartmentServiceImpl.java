package com.company.departmentservice.service;

import com.company.departmentservice.dto.DepartmentDTO;
import com.company.departmentservice.entity.Department;
import com.company.departmentservice.exception.DepartmentException;
import com.company.departmentservice.mapper.DepartmentMapper;
import com.company.departmentservice.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

//    using all args constructor for autowiring this instance.
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) throws DepartmentException  {

        // convert department dto to jpa entity

        Department department = DepartmentMapper.mapToDepartment(departmentDTO);

        Department savedDepartment = departmentRepository.save(department);


        DepartmentDTO savedDepartmentDTO = DepartmentMapper.mapToDepartmentDTO(savedDepartment);

        return savedDepartmentDTO;
    }

    @Override
    public DepartmentDTO getDepartmentCode(String departmentCode)  throws DepartmentException{
        Department department =  departmentRepository.findByDepartmentCode(departmentCode);
//        convert jpa to dto

        DepartmentDTO departmentDTO = DepartmentMapper.mapToDepartmentDTO(department);
        if(departmentDTO==null) {
            throw new DepartmentException("Service.DEPARTMENT_NOT_FOUND");
        }
        return departmentDTO;
    }
}
