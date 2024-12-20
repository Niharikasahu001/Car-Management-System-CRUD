package com.assignment.CarManagement.dao;


import com.assignment.CarManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
    User findByEmailAndPasswordAndRole(String email, String password, String role);
}

