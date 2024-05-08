package com.company.employeesservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDTO {

    private Long orgId;
    private String organizationName;
    private String organizationDescription;
    private String organizationCode;
    private LocalDateTime organizationCreatedDate;
}
