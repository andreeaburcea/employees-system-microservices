package com.company.departmentservice.controller;

import com.company.departmentservice.dto.DepartmentDTO;
import com.company.departmentservice.exception.DepartmentException;
import com.company.departmentservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Department Service-  DepartmentController",
        description = "Department Controller exposes REST APIs for Department Service"
)
@RestController
@RequestMapping("departments")
@AllArgsConstructor
public class DepartmentController {

    @Autowired
    private Environment environment;
    @Autowired
    private DepartmentService departmentService;

//    built save department REST API
    @Operation(
            summary = "Save Department REST API",
            description = "Save Department REST API is used to save department object in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 means CREATED."
    )
    @PostMapping
    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentDTO) throws DepartmentException {

        DepartmentDTO savedDepartment =  departmentService.saveDepartment(departmentDTO);
        String successMessage = environment.getProperty("API.INSERT_SUCCESS") + savedDepartment;
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

//    build GET department REST API
    // localhost:8080/api/departments/IT001
@Operation(
        summary = "GET Department REST API",
        description = "GET Department REST API is used to GET department object in a database"
)
@ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 means SUCCESS."
)
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDTO> getDepartmentCode(@PathVariable("department-code") String departmentCode) throws DepartmentException{
        DepartmentDTO departmentDTO = departmentService.getDepartmentCode(departmentCode);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }
}
