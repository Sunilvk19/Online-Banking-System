package com.onlinebank.OnlineBankingSystem.request;

import com.onlinebank.OnlineBankingSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class LoginRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
    private Role role;
}
