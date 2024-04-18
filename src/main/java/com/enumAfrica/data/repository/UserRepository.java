package com.enumAfrica.data.repository;

import com.enumAfrica.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    User findUserByFirstName(String name);
}
