package com.enumAfrica.services;

import com.enumAfrica.data.model.User;
import com.enumAfrica.exception.AdminNotFoundException;
import jakarta.persistence.Access;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;
    @Test
    public void testThatExistsByDefault() throws AdminNotFoundException {
        User foundAdmin = adminService.findAdminByFirstName("admin");
        assertNotNull(foundAdmin);
    }


}