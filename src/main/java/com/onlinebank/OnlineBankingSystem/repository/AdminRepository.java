package com.onlinebank.OnlineBankingSystem.repository;

import com.onlinebank.OnlineBankingSystem.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Optional<Admin> findByUser_Email(String currentAdminName);
}
