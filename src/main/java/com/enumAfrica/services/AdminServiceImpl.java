package com.enumAfrica.services;

import com.enumAfrica.data.model.Role;
import com.enumAfrica.data.model.User;
import com.enumAfrica.exception.AdminNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final UserService userService;
    @Override
    public User findAdminByFirstName(String admin) throws AdminNotFoundException {
        User user = userService.findByFirstName(admin);
        if (user != null && user.getRole().equals(Role.ADMIN))return user;
        throw new AdminNotFoundException("Admin not found");
    }
}
