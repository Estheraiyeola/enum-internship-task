package com.enumAfrica.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.enumAfrica.data.model.Cohort;
import com.enumAfrica.data.model.Course;
import com.enumAfrica.data.model.Organization;
import com.enumAfrica.data.repository.OrganizationRepository;
import com.enumAfrica.dto.request.AuthenticateOrganizationRequest;
import com.enumAfrica.dto.request.RegisterOrganizationRequest;
import com.enumAfrica.dto.response.AuthenticatedOrganizationResponse;
import com.enumAfrica.dto.response.RegisteredOrganizationResponse;
import com.enumAfrica.exception.OrganizationAlreadyExistsException;
import com.enumAfrica.exception.OrganizationDoesNotExistException;
import com.enumAfrica.utils.Mapper;
import com.enumAfrica.utils.SecretKeyGenerator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService{
    private final Mapper mapper;
    private final OrganizationRepository organizationRepository;
    private final CohortService cohortService;
    private final CourseService courseService;
    @Override
    public RegisteredOrganizationResponse registerOrganization(RegisterOrganizationRequest registerOrganizationRequest) throws OrganizationAlreadyExistsException {
        Organization foundOrganization = organizationRepository.findOrganizationByName(registerOrganizationRequest.getName());
        if (foundOrganization == null){
            Organization mappedOrganization = mapper.map(registerOrganizationRequest);
            Organization savedOrganization = organizationRepository.save(mappedOrganization);

            RegisteredOrganizationResponse response = new RegisteredOrganizationResponse();
            response.setOrganization(savedOrganization);
            response.setMessage("Organization Registered Successfully");
            return response;
        }


        throw new OrganizationAlreadyExistsException("Organization Already Exists!!!");
    }

    @Override
    @Transactional
    public void deleteAll() {
        for (Organization organization:organizationRepository.findAll()) {
            List<Cohort> cohortList = cohortService.findCohortByOrganization(organization);
            for (Cohort cohort:cohortList) {
                cohort.getCourses().clear();
            }
            cohortService.deleteCohorts(cohortList);
            organizationRepository.delete(organization);
        }
    }

    @Override
    public AuthenticatedOrganizationResponse authenticateOrganization(AuthenticateOrganizationRequest authenticateOrganizationRequest) throws OrganizationDoesNotExistException {
        Organization foundOrganization = organizationRepository.findOrganizationByEmail(authenticateOrganizationRequest.getEmail());
        if (foundOrganization != null) {
            boolean isUser = BCrypt.checkpw(authenticateOrganizationRequest.getPassword(), foundOrganization.getPassword());
            if (isUser){
                SecretKeyGenerator secretKeyGenerator = new SecretKeyGenerator();
                LocalDateTime currentTime = LocalDateTime.now();
                LocalDateTime expirationTime = currentTime.plusHours(1);
                Date expiryDate = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());

                byte[] keyBytes = secretKeyGenerator.generateSecureRandomBytes();
                Algorithm algorithm = Algorithm.HMAC256(keyBytes);

                String accessToken = JWT.create()
                        .withClaim("id", foundOrganization.getId())
                        .withClaim("firstName", foundOrganization.getName())
                        .withClaim("email", foundOrganization.getEmail())
                        .withClaim("role", foundOrganization.getRole().toString())
                        .withIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                        .withExpiresAt(expiryDate)
                        .sign(algorithm);
                AuthenticatedOrganizationResponse response = new AuthenticatedOrganizationResponse();

                response.setAccessToken(accessToken);
                response.setMessage("Organization authenticated successfully");
                return response;
            }
        }
        throw new OrganizationDoesNotExistException("Organization Does Not Exist");
    }
    @Override
    public List<String> verifyToken(String accessToken) throws JWTDecodeException {
        List<String> storage = new ArrayList<>();
        DecodedJWT jwt = JWT.decode(accessToken);
        String id = jwt.getClaim("id").asString();
        String role = jwt.getClaim("role").asString();

        storage.add(id);
        storage.add(role);
        return storage;
    }

    @Override
    public void deleteOrganization(Organization organization) {
        organizationRepository.delete(organization);
    }

    @Override
    public List<Organization> getAllOrganizations(){
        return organizationRepository.findAll();
    }

    @Override
    public Organization findOrganizationById(Long organizationId) throws OrganizationDoesNotExistException {
        return organizationRepository.findById(organizationId).orElseThrow(()-> new  OrganizationDoesNotExistException("Organization does not exist"));
    }
}
