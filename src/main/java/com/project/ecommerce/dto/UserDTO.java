package com.project.ecommerce.dto;

import com.project.ecommerce.entity.UserModel;
import lombok.Data;

import java.io.Serializable;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class UserDTO implements Serializable {
    private Long id;
    private String email;
    private String password;
    private String name;

    public UserDTO() {
    }

    public UserDTO(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserModel convertToUserModel(){
        return new UserModel(this.id, this.email, this.password, this.name);
    }
}
