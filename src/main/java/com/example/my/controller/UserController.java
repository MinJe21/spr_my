package com.example.my.controller;

import com.example.my.dto.UserDto;
import com.example.my.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto.LoginReqDto req) {
        UserDto.LoginResDto res = userService.Login(req);
        if (res == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto.CreateResUser> signup(@RequestBody UserDto.CreateReqUser req) {
        return ResponseEntity.ok(userService.CreateUser(req));
    }


}
