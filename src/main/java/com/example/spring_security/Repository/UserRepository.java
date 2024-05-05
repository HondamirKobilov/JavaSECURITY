package com.example.spring_security.Repository;

import com.example.spring_security.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<Users, Integer> {
    boolean existsByLogin(String login);
}
