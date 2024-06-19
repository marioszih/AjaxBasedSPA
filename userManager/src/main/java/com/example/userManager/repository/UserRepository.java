package com.example.userManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userManager.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
