package com.enumAfrica.data.repository;

import com.enumAfrica.data.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Organization findOrganizationByName(String name);

    Organization findOrganizationByEmail(String email);
}
