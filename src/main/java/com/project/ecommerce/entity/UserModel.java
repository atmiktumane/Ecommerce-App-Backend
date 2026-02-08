package com.project.ecommerce.entity;

import com.project.ecommerce.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    public UserDTO convertToUserDTO(){
        return new UserDTO(this.id, this.email, this.password, this.name);
    }
}
