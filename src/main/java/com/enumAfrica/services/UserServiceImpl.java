package com.enumAfrica.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.enumAfrica.data.model.User;
import com.enumAfrica.data.repository.UserRepository;
import com.enumAfrica.dto.request.AuthenticateUserRequest;
import com.enumAfrica.dto.request.CreateUserRequest;
import com.enumAfrica.dto.response.AuthenticatedUserResponse;
import com.auth0.jwt.algorithms.Algorithm;
import com.enumAfrica.dto.response.CreatedUserResponse;
import com.enumAfrica.exception.UserAlreadyExistsException;
import com.enumAfrica.exception.UserWithThisCredentialsDoesNotExistException;
import com.enumAfrica.utils.Mapper;
import com.enumAfrica.utils.SecretKeyGenerator;
import jakarta.persistence.MappedSuperclass;
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
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Mapper mapper;
    @Override
    public CreatedUserResponse createUser(CreateUserRequest createUserRequest) throws UserAlreadyExistsException {
        User foundUser = userRepository.findUserByEmail(createUserRequest.getEmail());
        if (foundUser == null){
            User newUser = mapper.map(createUserRequest);
            User savedUser = userRepository.save(newUser);

            CreatedUserResponse createdUserResponse = new CreatedUserResponse();
            createdUserResponse.setMessage("User Successfully Created");
            createdUserResponse.setUser(savedUser);
            return createdUserResponse;
        }
        throw new UserAlreadyExistsException("User already exists");
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public User findByFirstName(String name) {
        return userRepository.findUserByFirstName(name);
    }

    @Override
    public boolean adminExists() {
        return findByFirstName("admin") != null;
    }

    @Override
    public AuthenticatedUserResponse authenticateUser(AuthenticateUserRequest authenticateUserRequest) throws UserWithThisCredentialsDoesNotExistException {
        User foundUser = userRepository.findUserByEmail(authenticateUserRequest.getEmail());
        if (foundUser!= null) {
            boolean isUser = BCrypt.checkpw(authenticateUserRequest.getPassword(), foundUser.getPassword());
            if (isUser){
                SecretKeyGenerator secretKeyGenerator = new SecretKeyGenerator();
                LocalDateTime currentTime = LocalDateTime.now();
                LocalDateTime expirationTime = currentTime.plusHours(1);
                Date expiryDate = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());

                byte[] keyBytes = secretKeyGenerator.generateSecureRandomBytes();
                Algorithm algorithm = Algorithm.HMAC256(keyBytes);

                String accessToken = JWT.create()
                        .withClaim("id", foundUser.getId())
                        .withClaim("firstName", foundUser.getFirstName())
                        .withClaim("lastName", foundUser.getLastName())
                        .withClaim("email", foundUser.getEmail())
                        .withClaim("role", foundUser.getRole().toString())
                        .withIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                        .withExpiresAt(expiryDate)
                        .sign(algorithm);
                AuthenticatedUserResponse response = new AuthenticatedUserResponse();

                response.setAccessToken(accessToken);
                response.setMessage("Successfully Authenticated");
                return response;
            }
        }
        throw new UserWithThisCredentialsDoesNotExistException("User Does Not Exist");
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


}
