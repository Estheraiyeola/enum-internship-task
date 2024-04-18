package com.enumAfrica.data.repository;

import com.enumAfrica.data.model.ProgramType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramTypeRepository extends JpaRepository<ProgramType, Long> {
    ProgramType findByName(String name);
}
