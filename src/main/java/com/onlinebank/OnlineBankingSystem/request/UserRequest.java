package com.onlinebank.OnlineBankingSystem.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onlinebank.OnlineBankingSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String email;
    private String password;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private Role role;
}
