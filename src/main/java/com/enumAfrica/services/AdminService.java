package com.enumAfrica.services;

import com.enumAfrica.data.model.User;
import com.enumAfrica.exception.AdminNotFoundException;

public interface AdminService {
    User findAdminByFirstName(String admin) throws AdminNotFoundException;
}
