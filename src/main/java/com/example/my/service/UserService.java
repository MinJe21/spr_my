package com.example.my.service;

import com.example.my.domain.User;
import com.example.my.dto.UserDto;
import com.example.my.repository.UserRepository;
import com.example.my.util.TokenFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenFactory tokenFactory;

    public UserDto.CreateResUser CreateUser(UserDto.CreateReqUser req) {
        User u = new User(null, false, req.getUsername(), req.getPassword());
        return userRepository.save(req.toEntity()).toCreateResDto();
    }


    public UserDto.LoginResDto Login(UserDto.LoginReqDto req) {
        User user = userRepository.findByUsernameAndPassword(req.getUsername(), req.getPassword());
        if(user == null){
            //throw new RuntimeException("id or password error!!");
            //로그인 실패
            return UserDto.LoginResDto.builder().refreshToken(null).build();
        } else {
            //로그인 성공
            String refreshToken = tokenFactory.generateRefreshToken(user.getUserId());
            System.out.println("refreshToken : " + refreshToken);

            Long id = tokenFactory.validateRefreshToken(refreshToken);
            System.out.println("id : " + id);
            return UserDto.LoginResDto.builder().refreshToken(refreshToken).build();
        }
    }
}

