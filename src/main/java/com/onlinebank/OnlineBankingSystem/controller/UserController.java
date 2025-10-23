package com.onlinebank.OnlineBankingSystem.controller;
import com.onlinebank.OnlineBankingSystem.entity.User;
import com.onlinebank.OnlineBankingSystem.request.UserRequest;
import com.onlinebank.OnlineBankingSystem.response.AdminResponse;
import com.onlinebank.OnlineBankingSystem.response.UserResponse;
import com.onlinebank.OnlineBankingSystem.service.UserService;
import com.onlinebank.OnlineBankingSystem.utility.ResponseStructure;
import com.onlinebank.OnlineBankingSystem.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final RestResponseBuilder responseBuilder;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@Valid @RequestBody UserRequest userRequest){
        UserResponse response = userService.registerUser(userRequest);
       return responseBuilder.success(HttpStatus.CREATED,"User is registered Successfully",response);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable Integer userId){
        UserResponse response = userService.findUserById(userId);
        return responseBuilder.success(HttpStatus.OK,"User is found",response);
    }
}
