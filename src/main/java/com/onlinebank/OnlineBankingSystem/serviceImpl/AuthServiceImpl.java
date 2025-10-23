package com.onlinebank.OnlineBankingSystem.serviceImpl;

import com.onlinebank.OnlineBankingSystem.request.LoginRequest;
import com.onlinebank.OnlineBankingSystem.response.JwtResponse;
import com.onlinebank.OnlineBankingSystem.security.JwtUtil;
import com.onlinebank.OnlineBankingSystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public JwtResponse authenticateUser(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),request.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boolean roleMatch = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_"+request.getRole()));
        if (!roleMatch){
            throw new RuntimeException("Role mismatches: Access denied for role"+request.getRole());
        }
        String token = jwtUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
