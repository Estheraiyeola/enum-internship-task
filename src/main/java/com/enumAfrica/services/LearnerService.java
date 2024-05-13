package com.enumAfrica.services;

import com.enumAfrica.data.model.Learner;

import java.util.List;

public interface LearnerService {
    List<Learner> getLearnersByOrganization(Long organizationId);
}
