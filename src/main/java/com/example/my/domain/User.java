package com.example.my.domain;

import com.example.my.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserRoleType> userRoleType = new ArrayList<>();

    //권한 관련한 기능 추가
    public List<UserRoleType> getRoleList(){
        if(!this.userRoleType.isEmpty()){
            return userRoleType;
        }
        return new ArrayList<>();
    }

    protected User(){}
    private User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User of(String username, String password){
        return new User(username, password);
    }

    public UserDto.CreateResUser toCreateResDto() {
        return UserDto.CreateResUser.builder().id(getUserId()).build();
    }
}
