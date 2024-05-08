package com.company.organizationservice.service;

import com.company.organizationservice.dto.OrganizationDTO;
import com.company.organizationservice.entity.Organization;
import com.company.organizationservice.exception.OrganizationException;

public interface OrganizationService {

//   return type as OrganizationDTo
    OrganizationDTO saveOrganization(OrganizationDTO organizationDTO) throws OrganizationException;

    // in service get, in repository findBy
    OrganizationDTO getOrganizationByCode(String organizationCode) throws OrganizationException;
}
