package com.expensesTracker.entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")//mango db ke sath kam karte hain collection create hoga user naam ka means table create hoga
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private String role;

}