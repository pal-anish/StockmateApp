package com.stockmateapp.userprofileservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    //@NotNull(message = "First Name cannot be null")
    @NotBlank(message = "First Name cannot be blank")
    private String firstname;

    @NotNull(message = "Last Name cannot be null")
    private String lastname;
    @NotNull(message = "Name is mandatory")
    @Column(unique = true)
    private String username;
    @NotNull(message = "Email is mandatory")
    @Email(message = "Please enter correct email")
    @Column(unique = true)
    private String email;
    @NotNull(message = "Password is mandatory")
    private String password;

    public User(long userId, String firstname, String lastname, String username, String email, String password) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
