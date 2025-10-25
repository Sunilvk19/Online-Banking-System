package com.onlinebank.OnlineBankingSystem.response;

import com.onlinebank.OnlineBankingSystem.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String email;
    private String role;
}
