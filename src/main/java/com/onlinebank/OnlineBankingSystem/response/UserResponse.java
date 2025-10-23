package com.onlinebank.OnlineBankingSystem.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onlinebank.OnlineBankingSystem.entity.Account;
import com.onlinebank.OnlineBankingSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Integer userId;
    private String username;
    private String email;
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private Integer age;
    private Role role;
    private Account accounts;
}
