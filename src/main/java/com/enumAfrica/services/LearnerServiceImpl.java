package com.enumAfrica.services;

import com.enumAfrica.data.model.Learner;
import com.enumAfrica.data.repository.LearnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LearnerServiceImpl implements LearnerService {
    private final LearnerRepository learnerRepository;
    @Override
    public List<Learner> getLearnersByOrganization(Long organizationId){
        return learnerRepository.findLearnersByOrganizationId(organizationId);
    }
}
