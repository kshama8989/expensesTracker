package com.expensesTracker.services;

import com.expensesTracker.dto.UserReqDTO;
import com.expensesTracker.dto.UserRespDTO;
import com.expensesTracker.entity.User;
import com.expensesTracker.repository.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration//beans ban ta hai or can write service
public class UserService {
@Autowired
    UserInterface userInterface;

public UserRespDTO createuser(UserReqDTO userReqDTO){
    User user=new User();
    user.setUsername(userReqDTO.getUsername());
    user.setEmail(userReqDTO.getEmail());
    user.setPassword(userReqDTO.getPassword());
    user.setRole(userReqDTO.getRole());

    User save = userInterface.save(user);
    return mapToResponse(save);
}

public UserRespDTO mapToResponse(User save){
    UserRespDTO userRespDTO=new UserRespDTO();
    userRespDTO.setEmail(save.getEmail());
    userRespDTO.setUsername(save.getUsername());
    userRespDTO.setRole(save.getRole());

    return userRespDTO;

}
public UserRespDTO getUserByEmail(UserReqDTO userReqDTO){
    UserRespDTO userRespDTO=new UserRespDTO();
    Optional<User> user=userInterface.findByEmail(userReqDTO.getEmail());
    if(user.isPresent()){
        userRespDTO.setRole(user.get().getRole());
        userRespDTO.setEmail(user.get().getEmail());
        userRespDTO.setUsername(user.get().getUsername());
    }
    return userRespDTO;

}

public  UserRespDTO validateUser(UserReqDTO userReqDTO){
    UserRespDTO userRespDTO=new UserRespDTO();
    System.out.println("userReqDTO :"+ userReqDTO.toString());
    Optional<User> user=userInterface.findByEmail(userReqDTO.getEmail());
    System.out.println("User :"+user);
    if(user.isPresent()){
        userRespDTO.setRole(user.get().getRole());
        userRespDTO.setEmail(user.get().getEmail());
        userRespDTO.setUsername(user.get().getUsername());

    }
    return userRespDTO;
}

public UserRespDTO modifyuser(UserReqDTO userReqDTO){
    System.out.println("userReqDTO :"+ userReqDTO.toString());
    Optional<User> user=userInterface.findByEmail(userReqDTO.getEmail());
    System.out.println("User :"+user);
    if(user.isPresent()){
        User existingUser = user.get();
        if(userReqDTO.getUsername()!=null && !userReqDTO.getUsername().isEmpty() ){
            existingUser.setUsername(userReqDTO.getUsername());
        }
        if(userReqDTO.getRole()!=null && !userReqDTO.getRole().isEmpty() ) {
            existingUser.setRole(userReqDTO.getRole());
        }
        User updatedUser = userInterface.save(existingUser);
        UserRespDTO userRespDTO = new UserRespDTO();
        userRespDTO.setUsername(updatedUser.getUsername());
        userRespDTO.setEmail(updatedUser.getEmail());
        userRespDTO.setRole(updatedUser.getRole());

        return userRespDTO;
    }

    return null;
}
}
