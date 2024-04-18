package com.enumAfrica.data.repository;

import com.enumAfrica.data.model.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CohortRepository extends JpaRepository<Cohort, Long> {
    Cohort findCohortByName(String cohortName);
}
