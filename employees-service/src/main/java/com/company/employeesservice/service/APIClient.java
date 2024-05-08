package com.company.employeesservice.service;

import com.company.employeesservice.dto.DepartmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//configure based url
//@FeignClient(url = "http://localhost:8080", value = "DEPARTMENT-SERVICE")
// this interface becomes as feign client
// will dynamically create an implementation for this interface
// within the interface, we declare the methods to which want to make a REST API call


// using load balancer to call the rest API available instance of microservice.
// load balancing -> if one server is down, will run its instance.
// pass service ID from Eureka and will take care of calling the available department service instace.
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

//    call rest api from dept controller
    // built get department rest api
    // configure based url: http:localhost:8080/departments/IT001
    //rest api call
    // will dynamically make an implementation.
    // we declare the method here and the cloud will do the rest.
    @GetMapping("departments/{department-code}")
    public DepartmentDTO getDepartmentCode(@PathVariable("department-code") String departmentCode);
}
