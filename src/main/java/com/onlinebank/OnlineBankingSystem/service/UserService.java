package com.onlinebank.OnlineBankingSystem.service;

import com.onlinebank.OnlineBankingSystem.entity.User;
import com.onlinebank.OnlineBankingSystem.request.UserRequest;
import com.onlinebank.OnlineBankingSystem.response.AdminResponse;
import com.onlinebank.OnlineBankingSystem.response.UserResponse;

import java.util.Optional;

public interface UserService {


    UserResponse registerUser(UserRequest userRequest);

    UserResponse findUserById(Integer userId);


    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String username);
}
