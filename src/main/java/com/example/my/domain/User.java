package com.example.my.domain;

import com.example.my.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private boolean deleted;
    private String username;
    private String password;

    public static User of(String username, String password){
        return new User(null, false, username, password);
    }

    public UserDto.CreateResUser toCreateResDto() {
        return UserDto.CreateResUser.builder().id(getUserId()).build();
    }
}
