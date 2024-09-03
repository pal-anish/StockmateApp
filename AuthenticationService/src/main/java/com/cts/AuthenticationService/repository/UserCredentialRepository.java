package com.cts.AuthenticationService.repository;

import com.cts.AuthenticationService.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential,String> {

    @Query("SELECT u FROM UserCredential  AS  u WHERE u.username = :username AND u.password = :password")
    UserCredential findByUsernameAndPassword(String username, String password);


}
