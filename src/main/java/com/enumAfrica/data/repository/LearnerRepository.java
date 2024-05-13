package com.enumAfrica.data.repository;

import com.enumAfrica.data.model.Learner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LearnerRepository extends JpaRepository<Learner, Long> {
    List<Learner> findLearnersByOrganizationId(Long organizationId);
}
