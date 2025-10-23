package com.onlinebank.OnlineBankingSystem.serviceImpl;

import com.onlinebank.OnlineBankingSystem.entity.Admin;
import com.onlinebank.OnlineBankingSystem.entity.User;
import com.onlinebank.OnlineBankingSystem.enums.Role;
import com.onlinebank.OnlineBankingSystem.exceptionhandler.EmailNotFoundException;
import com.onlinebank.OnlineBankingSystem.exceptionhandler.UserNotFoundException;
import com.onlinebank.OnlineBankingSystem.repository.AdminRepository;
import com.onlinebank.OnlineBankingSystem.repository.UserRepository;
import com.onlinebank.OnlineBankingSystem.request.UserRequest;
import com.onlinebank.OnlineBankingSystem.response.AdminResponse;
import com.onlinebank.OnlineBankingSystem.response.UserResponse;
import com.onlinebank.OnlineBankingSystem.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private UserResponse mapToResponse(User user){
        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .age(user.getAge())
                .accounts(user.getAccounts())
                .dateOfBirth(user.getDateOfBirth())
                .role(user.getRole())
                .build();
    }
    private User mapToUser(UserRequest request, User user){
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setAge(request.getAge());
            user.setDateOfBirth(request.getDateOfBirth());
            if(request.getPassword() != null && !request.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            }
            return user;
    }

    @Override
    public UserResponse registerUser(UserRequest userRequest) {
        if(userRepository.existsByEmail(userRequest.getEmail())){
            throw new EmailNotFoundException("Email is not Found!!");
        }
        User user = new User();
        this.mapToUser(userRequest,user);
        user.setRole(Role.USER);
        User saveuser = userRepository.save(user);
        return this.mapToResponse(saveuser);
    }

    @Override
    public UserResponse findUserById(Integer userId) {
        Optional<User> optional = userRepository.findById(userId);
        if(optional.isEmpty()){
            throw new UserNotFoundException("User is not found");
        }
        return this.mapToResponse(optional.get());
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public Optional<User> getUserByEmail(String username) {
        return userRepository.findByEmail(username);
    }


}
