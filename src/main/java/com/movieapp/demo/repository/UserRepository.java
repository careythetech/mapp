package com.movieapp.demo.repository;

import com.movieapp.demo.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Interface that would connect with the Database
// We need to find it by Id
// fetch user by email
// This is basically a query
public interface  UserRepository extends JpaRepository<UserEntity, Long> {
    // Find by username
    // Find by email
    UserEntity findByUsername(String username);

}
