package com.project.ecommerce.entity;

import com.project.ecommerce.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    public UserModel() {
    }

    public UserModel(Long id, String email, String password, String name) {
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

    public UserDTO convertToUserDTO(){
        return new UserDTO(this.id, this.email, this.password, this.name);
    }
}
