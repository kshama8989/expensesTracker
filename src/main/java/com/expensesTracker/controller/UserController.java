package com.expensesTracker.controller;

import com.expensesTracker.dto.UserReqDTO;
import com.expensesTracker.dto.UserRespDTO;
import com.expensesTracker.repository.UserInterface;
import com.expensesTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class UserController {
    // This class will handle the user-related operations
    // such as registration, login, and profile management
    // You can use Spring's @RestController annotation to create RESTful APIs
    // and inject the userInterface repository to perform CRUD operations on users

    // Example of a method to register a new user
    // @PostMapping("/register")
    // public ResponseEntity<User> registerUser(@RequestBody User user) {
    //     // Logic to save the user in the database
    //     return ResponseEntity.ok(savedUser);
    // }

    // Example of a method to login a user
    // @PostMapping("/login")
    // public ResponseEntity<User> loginUser(@RequestBody User user) {
    //     // Logic to authenticate the user
    //     return ResponseEntity.ok(authenticatedUser);
    // }
    @Autowired
    private UserService userService;

    @PostMapping("/adduser")
    public ResponseEntity<?> adduser(@RequestBody UserReqDTO userReqDTO) {
       UserRespDTO userDetail= userService.getUserByEmail(userReqDTO);
       //checking that if user is already exist if not create other wise error already exist.
       if(userDetail.getEmail()!=null) {
           Map<String ,String> error=new HashMap<>();
           error.put("message","User already exist");
            return ResponseEntity.badRequest().body(error);
       }
           UserRespDTO userRespDTO = userService.createuser(userReqDTO);



        return new ResponseEntity<>(userRespDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserReqDTO userReqDTO){
       UserRespDTO userRespDTO= userService.validateUser(userReqDTO);
        // Case 1: validateUser returns null (user not found)
        if (userRespDTO == null || userRespDTO.getEmail() == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        // Case 2: Email does not match input (could be a logic issue)
        if (!userRespDTO.getEmail().equalsIgnoreCase(userReqDTO.getEmail())) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        // If everything is valid
        Map<String, String> response = new HashMap<>();
        response.put("message", "Success");
        return ResponseEntity.ok(response);

    }
    @PutMapping("/modify")
    public ResponseEntity modifyUser(@RequestBody UserReqDTO userReqDTO){
        UserRespDTO userRespDTO = userService.modifyuser(userReqDTO);
        if (userRespDTO!=null) {
            return ResponseEntity.ok(userRespDTO);
        }


        Map<String, String> error = new HashMap<>();
        error.put("message", "Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }


}
