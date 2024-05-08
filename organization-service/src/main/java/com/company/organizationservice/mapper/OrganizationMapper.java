package com.company.organizationservice.mapper;

import com.company.organizationservice.dto.OrganizationDTO;
import com.company.organizationservice.entity.Organization;

public class OrganizationMapper {

    //convert jpa to dto
    public static OrganizationDTO mapToOrganizationDTO(Organization organization) {
        OrganizationDTO organizationDTO = new OrganizationDTO(
                organization.getOrgId(),
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationCode(),
                organization.getOrganizationCreatedDate()
        );
        return organizationDTO;
    }

    // convert dto to jpa
    public static Organization mapToOrganization(OrganizationDTO organizationDTO) {
        Organization organization = new Organization(
                organizationDTO.getOrgId(),
                organizationDTO.getOrganizationName(),
                organizationDTO.getOrganizationDescription(),
                organizationDTO.getOrganizationCode(),
                organizationDTO.getOrganizationCreatedDate()
        );
        return  organization;
    }
}
