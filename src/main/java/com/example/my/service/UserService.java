package com.example.my.service;

import com.example.my.domain.User;
import com.example.my.dto.UserDto;
import com.example.my.repository.UserRepository;
import com.example.my.util.TokenFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenFactory tokenFactory;

    public UserDto.CreateResUser CreateUser(UserDto.CreateReqUser req) {
        Optional<User> existing = userRepository.findByUsername(req.getUsername());
        if (existing.isPresent()) {
            throw new RuntimeException("이미 존재하는 username입니다.");
        }
        return userRepository.save(req.toEntity()).toCreateResDto();
    }


    public UserDto.LoginResDto Login(UserDto.LoginReqDto req) {
        User user = userRepository.findByUsernameAndPassword(req.getUsername(), req.getPassword());
        if(user == null){
            return UserDto.LoginResDto.builder().refreshToken(null).build();
        } else {
            String refreshToken = tokenFactory.generateRefreshToken(user.getUserId());
            System.out.println("refreshToken : " + refreshToken);

            Long id = tokenFactory.validateRefreshToken(refreshToken);
            System.out.println("id : " + id);
            return UserDto.LoginResDto.builder().refreshToken(refreshToken).build();
        }
    }
}

