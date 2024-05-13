package com.enumAfrica.data.repository;

import com.enumAfrica.data.model.Cohort;
import com.enumAfrica.data.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CohortRepository extends JpaRepository<Cohort, Long> {
    Cohort findCohortByName(String cohortName);
    List<Cohort> findCohortsByOrganization(Organization organization);
}
