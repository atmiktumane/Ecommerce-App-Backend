package com.project.ecommerce.service;

import com.project.ecommerce.dto.LoginRequest;
import com.project.ecommerce.dto.RegisterRequest;
import com.project.ecommerce.dto.UserDTO;

public interface UserService {
    UserDTO register(RegisterRequest request);

    UserDTO login(LoginRequest request);

    UserDTO getUserByEmail(String email);

}
