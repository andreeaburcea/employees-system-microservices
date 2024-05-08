package com.company.employeesservice.service;

import com.company.employeesservice.dto.APIResponseDTO;
import com.company.employeesservice.dto.DepartmentDTO;
import com.company.employeesservice.dto.EmployeeDTO;
import com.company.employeesservice.dto.OrganizationDTO;
import com.company.employeesservice.entity.Employee;
import com.company.employeesservice.exception.EmployeeException;
import com.company.employeesservice.mapper.EmployeeMapper;
import com.company.employeesservice.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private EmployeeRepository employeeRepository;
    // inject rest template, instance variables of restTemplate spring bean
    //private RestTemplate restTemplate;

    //private EmployeeMapper employeeMapper;

    @Autowired
    private WebClient webClient;
    //ferign client
    //@Autowired

    //@Autowired
    private APIClient apiClient;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws EmployeeException {
//
        Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);
        //save employee jpa object
        Employee savedEmployee = employeeRepository.save(employee);
        //convert savedEmployee to a dto
        EmployeeDTO savedEmployeeDTO = EmployeeMapper.mapToEmployeeDTO(savedEmployee);
        return savedEmployeeDTO;

    }

    // instead oe EmployeeDTO we have to pass the APIResponseDTO


    // from resilience4j
    // down you can see the fallback method
    // @CircuitBreaker(name="${spring.application.name}", fallback="getDefaultDepartment")
    @CircuitBreaker()

    //@Retry(name="${spring.application.name}, fallback="getDefaultDepartment")
    @Override
    public APIResponseDTO getEmployeeById(Long empId) throws EmployeeException {

//        get() is used to retrieve the Employee object from the Optional<Employee> returned by findById(id)
//         It's generally safer to use methods like .orElse() or .orElseThrow()
        LOGGER.info("inside getEmployeeById() method");
        Employee employee = employeeRepository.findById(empId).get();

        //make rest api call
        // url & response will be the dept dto class
        //ResponseEntity<DepartmentDTO> responseEntity =  restTemplate.getForEntity("http://localhost:8080/departments/" +employee.getDepartmentCode(), DepartmentDTO.class);
        // we got a dept dto
        // send this to the client and add APIResponseDTO
        //DepartmentDTO departmentDTO = responseEntity.getBody();


//        web client
        // we make a get api call
        // retrieve the department
        // block - > for sync
        // call bodyToMono method to pass a response type -> reactive terms
        // we pass the department to body to mono
        DepartmentDTO departmentDTO = webClient.get()
                .uri("http://localhost:8080/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDTO.class)
                .block();

//        get organization in the response
        OrganizationDTO organizationDTO = webClient.get()
                .uri("http://localhost:8083/organizations/" +employee.getOrganizationCode())
                .retrieve()
                //return type is organization dto
                .bodyToMono(OrganizationDTO.class)
                .block();

      //  will use a feign client instead of web client

        //DepartmentDTO departmentDTO = apiClient.getDepartmentCode(employee.getDepartmentCode());

//
        EmployeeDTO employeeDTO = EmployeeMapper.mapToEmployeeDTO(employee);

        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployeeDTO(employeeDTO);
        apiResponseDTO.setDepartmentDTO(departmentDTO);
        apiResponseDTO.setOrganizationDTO(organizationDTO);
        //return employeeDTO;
        if(apiResponseDTO == null) {
            throw  new EmployeeException("Service.EMPLOYEE_NOT_FOUND");
        }
        return apiResponseDTO;
    }

    // using web client for 2 separated calls.

    @Override
    public Mono<APIResponseDTO> findByEmployeeAndDepartmentId(Long empId, Long deptId) throws EmployeeException  {
        Mono<EmployeeDTO> employeeMono = webClient
                .get()
                .uri("http://employee-service/employees/{empId}", empId)
                .retrieve()
                .bodyToMono(EmployeeDTO.class);

        Mono<DepartmentDTO> departmentMono = webClient
                .get()
                .uri("http://department-service/departments/{deptId}", deptId)
                .retrieve()
                .bodyToMono(DepartmentDTO.class);

        //Mono.zip for the combination of both results from web client into one Mono

        return Mono.zip(employeeMono, departmentMono)
                .map(tuple -> {
                    EmployeeDTO employeeDTO = tuple.getT1();
                    DepartmentDTO departmentDTO = tuple.getT2();

                    // we map the result to the api response object.
                    APIResponseDTO apiResponseDTO = new APIResponseDTO();
                    apiResponseDTO.setEmployeeDTO(employeeDTO);
                    apiResponseDTO.setDepartmentDTO(departmentDTO);
                    return apiResponseDTO;
                });
    }

    // mono emits one element -> the result of the async operation
    // reactive programming. Mono can be combined, mapped and filtered in reactive mode for different operations on your data.

    @Override
    public EmployeeDTO updateEmployee(Long empId) throws EmployeeException {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        // retrieve the employee by id from the repo
        Employee existingEmployee = employeeRepository.findById(empId).get();

        // update the existing employee with the new data
        existingEmployee.setFirstName(employeeDTO.getFirstName());
        existingEmployee.setLastName(employeeDTO.getLastName());
        existingEmployee.setEmail(employeeDTO.getEmail());

        //save the updated employee back to the repository
        Employee updatedEmployee = employeeRepository.save(existingEmployee);

//        // map updated employee to dto and return it


        EmployeeDTO updatedEmployeeDTO = EmployeeMapper.mapToEmployeeDTO(updatedEmployee);
        //return EmployeeMapper.mapToEmployeeDTO(updatedEmployee);
        if(updatedEmployeeDTO == null) {
            throw new EmployeeException("Service.UPDATE_FAILED");
        }
        return updatedEmployeeDTO;
    }

    @Override
    public void deleteEmployeeByEmpId(Long empId) throws EmployeeException {

       // employeeRepository.deleteEmployeeByEmpId(empId);
        employeeRepository.deleteById(empId);

    }

    //fallback method
    // write the logic to return the default department
    // copy the code from getEmployeeById
    // instead of making a call to department service, we are create one default dept
    // we are adding this default dept to a response.
    public APIResponseDTO getDefaultDepartment(Long empId, Exception exception) {

        LOGGER.info("after getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(empId).get();

        // create default department
        DepartmentDTO departmentDTO = new DepartmentDTO();
        // pass default values for department object
        departmentDTO.setDepartmentName(("R&D Department"));
        departmentDTO.setDepartmentCode("RD001");
        departmentDTO.setDepartmentDescription("Research and Development department");


        EmployeeDTO employeeDTO = EmployeeMapper.mapToEmployeeDTO(employee);

        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployeeDTO(employeeDTO);
        apiResponseDTO.setDepartmentDTO(departmentDTO);
        //return employeeDTO;
        return apiResponseDTO;
    }


}
