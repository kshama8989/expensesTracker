package com.expensesTracker.dto;

import lombok.Data;

@Data
public class UserRespDTO {
    private String username;
    private String email;
    private String role;
}
