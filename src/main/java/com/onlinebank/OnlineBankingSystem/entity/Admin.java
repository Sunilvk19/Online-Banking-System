package com.onlinebank.OnlineBankingSystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId") // Foreign key
    private User user;
}
