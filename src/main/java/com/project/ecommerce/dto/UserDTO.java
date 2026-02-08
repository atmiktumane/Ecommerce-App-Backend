package com.project.ecommerce.dto;

import com.project.ecommerce.entity.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable { // Make DTO Serializable
    private Long id;
    private String email;
    private String password;
    private String name;

    public UserModel convertToUserModel(){
        return new UserModel(this.id, this.email, this.password, this.name);
    }
}
