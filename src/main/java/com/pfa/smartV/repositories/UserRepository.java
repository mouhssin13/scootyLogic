package com.pfa.smartV.repositories;

import com.pfa.smartV.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByCIN(String cin);
}
