package com.expensesTracker.dto;

import lombok.Data;

@Data
public class UserReqDTO {

    private String username;
    private String email;
    private String password;
    private String role;

}
