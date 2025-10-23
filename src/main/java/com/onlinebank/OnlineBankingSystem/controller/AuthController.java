package com.onlinebank.OnlineBankingSystem.controller;

import com.onlinebank.OnlineBankingSystem.request.LoginRequest;
import com.onlinebank.OnlineBankingSystem.response.JwtResponse;
import com.onlinebank.OnlineBankingSystem.service.AuthService;
import com.onlinebank.OnlineBankingSystem.utility.ResponseStructure;
import com.onlinebank.OnlineBankingSystem.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<JwtResponse>> login(@Valid @RequestBody LoginRequest request) {
        System.out.println("Incoming Login Request "+request);
        JwtResponse response = authService.authenticateUser(request);
        return responseBuilder.success(HttpStatus.OK, "Login Successfully", response);
    }
}
