package com.example.my.service;

import com.example.my.domain.RoleType;
import com.example.my.domain.User;
import com.example.my.domain.UserRoleType;
import com.example.my.dto.UserDto;
import com.example.my.repository.RoleTypeRepository;
import com.example.my.repository.UserRepository;
import com.example.my.repository.UserRoleTypeRepository;
import com.example.my.util.TokenFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenFactory tokenFactory;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleTypeRepository roleTypeRepository;
    private final UserRoleTypeRepository userRoleTypeRepository;

    public UserDto.CreateResUser CreateUser(UserDto.CreateReqUser req) {
        User u = userRepository.findByUsername(req.getUsername());
        if(u != null){
            throw new RuntimeException("already exist");
        }
        req.setPassword(bCryptPasswordEncoder.encode(req.getPassword()));
        User newUser = userRepository.save(req.toEntity());

        //유저 롤 등 고려하기!
        RoleType roleType = roleTypeRepository.findByTypeName("ROLE_USER");
        if(roleType == null){
            roleType = RoleType.of("user", "ROLE_USER");
            roleTypeRepository.save(roleType);
        }
        UserRoleType newUserRoleType = UserRoleType.of(newUser, roleType);
        userRoleTypeRepository.save(newUserRoleType);
        return newUser.toCreateResDto();
    }


//    public UserDto.LoginResDto Login(UserDto.LoginReqDto req) {
//        User user = userRepository.findByUsernameAndPassword(req.getUsername(), req.getPassword());
//        if(user == null){
//            return UserDto.LoginResDto.builder().refreshToken(null).build();
//        } else {
//            String refreshToken = tokenFactory.generateRefreshToken(user.getUserId());
//            System.out.println("refreshToken : " + refreshToken);
//
//            Long id = tokenFactory.validateRefreshToken(refreshToken);
//            System.out.println("id : " + id);
//            return UserDto.LoginResDto.builder().refreshToken(refreshToken).build();
//        }
//    }
}

