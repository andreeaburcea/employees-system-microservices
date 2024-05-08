package com.company.organizationservice.controller;

import com.company.organizationservice.dto.OrganizationDTO;
import com.company.organizationservice.exception.OrganizationException;
import com.company.organizationservice.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@Tag(
        name = "Organization Service - Organization Controller",
        description = "OrganizationController exposes REST APIs for Organization Service"
)
@RestController
@RequestMapping("organizations")
@AllArgsConstructor
public class OrganizationController {

    private OrganizationService organizationService;


    //response entity is a generic so put the type
    // request body to change the JSON into java object.
    // http://localhost:8083/organizations
    // choose body. raw and JSON create a json object in postman
    /*
    * {
    * "id":1,
    * "organizatioName": "ABC",
    * "organizationDescription:"ABC Organization Description",
    * "organizationCode": "ABC_ORG",
    * "createdDate: "2024-04-26"
    * }
    * */
    @Operation(
            summary = "Save Organization REST API",
            description = "Save Organization REST API is used to save organization object in the database."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 means CREATED."
    )
    @PostMapping()
    public ResponseEntity<OrganizationDTO> savedOrganization(@RequestBody OrganizationDTO organizationDTO) throws OrganizationException {
        OrganizationDTO savedOrganization = organizationService.saveOrganization(organizationDTO);
        return  new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    // build organization by code REST API
    // http:localhost:8083/organizations/ABC_ORG
    @Operation(
            summary = "GET Organization REST API",
            description = "GET Organization REST API is used to GET organization object in the database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 means SUCCESS."
    )
    @GetMapping("/{code}")
    public ResponseEntity<OrganizationDTO> getOrganizationByCode(@PathVariable("code") String organizationCode) throws OrganizationException {
        OrganizationDTO organizationCodeDTO = organizationService.getOrganizationByCode(organizationCode);
        return  new ResponseEntity<>(organizationCodeDTO, HttpStatus.OK);
    }
}
