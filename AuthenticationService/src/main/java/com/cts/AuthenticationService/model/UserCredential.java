package com.cts.AuthenticationService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserCredential")
public class UserCredential {

    @Id
    private String username;
    private String password;

//    private String userid;

    public UserCredential() {
        super();
    }
    public UserCredential(String username, String password) {

        this.username = username;
        this.password = password;
    }

//    public UserCredential(String username, String password, String userid) {
//        this.username = username;
//        this.password = password;
//        this.userid = userid;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getUserid() {
//        return userid;
//    }
//
//    public void setUserid(String userid) {
//        this.userid = userid;
//    }
}
