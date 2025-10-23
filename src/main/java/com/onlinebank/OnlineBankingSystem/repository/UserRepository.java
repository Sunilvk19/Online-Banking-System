package com.onlinebank.OnlineBankingSystem.repository;

import com.onlinebank.OnlineBankingSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String currentUsername);

    boolean existsByEmail(String email);
}

