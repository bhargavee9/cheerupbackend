package com.cheerup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cheerup.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

   User findByUsername(String username);

   Optional<User> findByUsernameAndPassword(String username, String password);
   
}