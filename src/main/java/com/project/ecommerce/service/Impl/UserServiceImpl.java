package com.project.ecommerce.service.Impl;

import com.project.ecommerce.dto.LoginRequest;
import com.project.ecommerce.dto.RegisterRequest;
import com.project.ecommerce.dto.UserDTO;
import com.project.ecommerce.entity.UserModel;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @CacheEvict(value = "users", key = "#req.email")
    public UserDTO register(RegisterRequest req) {

        // Check if Email already exists
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        UserModel user = new UserModel();

        // Set Name & Email
        user.setName(req.getName());
        user.setEmail(req.getEmail());

        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        // Save User data in DB
        UserModel savedUser = userRepository.save(user);

        // Prepare Response body
        UserDTO response = new UserDTO();

        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());

        return response;
    }

    @Override
    public UserDTO login(LoginRequest req) {
        // Check if User is present or not
        UserModel user = userRepository.findByEmail(req.getEmail()).orElseThrow(()-> new RuntimeException("Invalid Email or Password"));

        // Match Password
        if(!passwordEncoder.matches(req.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Email or Password");
        }

//        System.out.println("User Data : "+ user.convertToUserDTO());

        UserDTO userDTO = user.convertToUserDTO();

        userDTO.setPassword("");

        // Prepare Login Response body
        return userDTO;
    }

    @Override
    @Cacheable(value="users", key="#email") // Cacheable Annotation
    public UserDTO getUserByEmail(String email) {

        System.out.println("Fetch Data from Database");

        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDTO userDTO = user.convertToUserDTO();

        userDTO.setPassword("");

        // Prepare Response body
        return userDTO;
    }


}
