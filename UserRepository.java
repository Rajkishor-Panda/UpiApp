package com.upiapp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upiapp.demo.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    // String = primary key type (mobile)
}
