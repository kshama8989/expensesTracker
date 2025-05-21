package com.expensesTracker.repository;

import com.expensesTracker.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserInterface extends MongoRepository<User,String> {

    Optional<User> findByEmail(String email);

    // This interface will automatically provide CRUD operations for the User entity
    // You can add custom query methods here if needed
    // For example, you can add a method to find a user by username:
    // User findByUsername(String username);

    // User findByPassword(String password);
    // User findById(String id);
    // User findByUsernameAndPassword(String username, String password);
    // User findByEmailAndPassword(String email, String password);
    // User findByIdAndPassword(String id, String password);
    // User findByIdAndUsername(String id, String username);



}
