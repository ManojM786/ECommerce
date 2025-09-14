package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserManagementDAO extends JpaRepository<UserData , Integer> {

    Optional<UserData> findByEmail(String email);
}
