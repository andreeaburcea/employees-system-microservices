package com.company.organizationservice.service;

import com.company.organizationservice.dto.OrganizationDTO;
import com.company.organizationservice.entity.Organization;
import com.company.organizationservice.exception.OrganizationException;
import com.company.organizationservice.mapper.OrganizationMapper;
import com.company.organizationservice.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    // we use All args constructor to not do a contructor for this
    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDTO saveOrganization(OrganizationDTO organizationDTO) throws OrganizationException {
        // convert org dto to jpa
        Organization organization = OrganizationMapper.mapToOrganization(organizationDTO);
        // save the above object in database
        Organization savedOrganization = organizationRepository.save(organization);
        // convert jpa to dto
        OrganizationDTO savedOrganizationDTO = OrganizationMapper.mapToOrganizationDTO(savedOrganization);
        return savedOrganizationDTO;
    }

    @Override
    public OrganizationDTO getOrganizationByCode(String organizationCode) throws OrganizationException {

        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        OrganizationDTO organizationDTO = OrganizationMapper.mapToOrganizationDTO(organization);
        if(organizationDTO==null) {
            throw new OrganizationException("Service.ORGANIZATION_NOT_FOUND");
        }
        return organizationDTO;
    }
}
