package com.onlinebank.OnlineBankingSystem.security;

import com.onlinebank.OnlineBankingSystem.entity.User;
import com.onlinebank.OnlineBankingSystem.exceptionhandler.UserNotFoundException;
import com.onlinebank.OnlineBankingSystem.repository.UserRepository;
import com.onlinebank.OnlineBankingSystem.service.UserService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(this::mapToUserDetails)
                .orElseThrow(()-> new UserNotFoundException("User is not Found"));
    }

    private UserDetails mapToUserDetails(@NotNull User user) {
        return (UserDetails) User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
