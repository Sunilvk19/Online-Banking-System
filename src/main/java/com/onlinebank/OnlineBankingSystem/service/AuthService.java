package com.onlinebank.OnlineBankingSystem.service;

import com.onlinebank.OnlineBankingSystem.request.LoginRequest;
import com.onlinebank.OnlineBankingSystem.response.JwtResponse;

public interface AuthService {
    JwtResponse authenticateUser(LoginRequest request);
}
