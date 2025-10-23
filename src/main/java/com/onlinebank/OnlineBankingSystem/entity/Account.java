package com.onlinebank.OnlineBankingSystem.entity;

import com.onlinebank.OnlineBankingSystem.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;
    private Integer accountNumber;
    private Double balance;
    private AccountType accountType;
    @OneToOne
    private User user;
    private Double transaction;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
