package com.company.employeesservice.controller;


import com.company.employeesservice.dto.APIResponseDTO;
import com.company.employeesservice.dto.EmployeeDTO;
import com.company.employeesservice.exception.EmployeeException;
import com.company.employeesservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/*
* @Tag(
        name = "Employee Service - EmployeeController",
        description = "EmployeeController exposes REST APIs for Employee Service"
)
* */
@RestController
@RequestMapping("employees")
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private Environment environment;
    @Autowired
    private EmployeeService employeeService;


    // now in postman create a json with the data to see if the request is working
//    @Operation(
//            summary = "Save Employee REST API",
//            description = "Save Employee REST API is used to save employee object in the database."
//    )
//    @ApiResponse(
//            responseCode = "201",
//            description = "HTTP Status 201 means CREATED."
//    )
    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) throws EmployeeException {
        EmployeeDTO savedEmployee =  employeeService.saveEmployee(employeeDTO);
        String successMessage = environment.getProperty("API.INSERT_SUCCESS") + savedEmployee;
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    // get method for employee rest api localhost:8081/employees/1
    // we return the api response dto instead of employee dto

    //    @Operation(
//            summary = "GET Employee REST API",
//            description = "GET Employee REST API is used to GET employee object in the database."
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "HTTP Status 200 means SUCCESS."
//    )
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDTO> getEmployeeById(@PathVariable("id") Long empId) throws EmployeeException {
        APIResponseDTO apiResponseDTO = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }


    //    @Operation(
//            summary = "GET Employee and Department REST API",
//            description = "GET Employee and Department REST API is used to GET employee object and department object in the database."
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "HTTP Status 200 means SUCCESS."
//    )
    @GetMapping("eId/dId")
    public Mono<ResponseEntity<APIResponseDTO>> findByEmployeeAndDepartmentId(@PathVariable("eId") Long empId, @PathVariable("dId") Long deptId) throws EmployeeException {
        // using block() for the Mono awaiting for obtaning the result in sync mode.
        //APIResponseDTO apiResponseDTO = employeeService.findByEmployeeAndDepartmentId(empId, deptId).block();
        //return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);

        Mono<ResponseEntity<APIResponseDTO>> apiResponseDTO = employeeService.findByEmployeeAndDepartmentId(empId, deptId)
                .map(response -> ResponseEntity.ok()
                        .body(response))
                .defaultIfEmpty(ResponseEntity.notFound().build());

        return apiResponseDTO;
    }


    //    @Operation(
//            summary = "PUT Employee REST API",
//            description = "PUT Employee REST API is used to update employee object in the database."
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "HTTP Status 200 means OK."
//    )
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") Long empId) throws EmployeeException {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(empId);
        String successMessage = environment.getProperty("API.UPDATE_SUCCESS" + empId);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }


// delete method for employee rest api
//    @Operation(
//            summary = "DELETE Employee REST API",
//            description = "DELETE Employee REST API is used to DELETE employee object in the database."
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "HTTP Status 200 means OK."
//    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployeeByEmpId(@PathVariable("id") Long empId) throws EmployeeException{
        employeeService.deleteEmployeeByEmpId(empId);
        String successMessage = environment.getProperty("API.DELETE_SUCCESS");
        return new ResponseEntity<>("Employee successfully deleted", HttpStatus.OK);
    }

}
