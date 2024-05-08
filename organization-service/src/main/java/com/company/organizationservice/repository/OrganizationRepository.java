package com.company.organizationservice.repository;

import com.company.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    // creating query data in jpa
    Organization findByOrganizationCode(String organizationCode);
}
