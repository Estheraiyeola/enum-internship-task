package com.enumAfrica.utils;

import com.enumAfrica.data.model.Role;
import com.enumAfrica.dto.request.CreateUserRequest;
import com.enumAfrica.exception.UserAlreadyExistsException;
import com.enumAfrica.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Override
    public void run(String... args) throws Exception {
        if (userService.adminExists()) {
            return;
        }

        CreateUserRequest request = new CreateUserRequest();
        request.setFirstName("admin");
        request.setPassword("password");
        request.setLastName("admin");
        request.setEmail("estheraiyeola8@yahoo.com");
        request.setRole(Role.ADMIN);
        try {
            userService.createUser(request);
        } catch (UserAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }
}
