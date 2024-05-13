package com.enumAfrica.data.repository;

import com.enumAfrica.data.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    List<Instructor> findInstructorsByOrganizationId(Long organizationId);
}
