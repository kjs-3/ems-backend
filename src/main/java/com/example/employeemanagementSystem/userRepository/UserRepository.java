package com.example.employeemanagementSystem.userRepository;

import com.example.employeemanagementSystem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    boolean existsByuserName(String username);

   Optional<Users> findByuserName(String username);
}
