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

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserDto.LoginReqDto req) {
//        try {
//            UserDto.LoginResDto res = userService.Login(req);
//            return ResponseEntity.ok(res);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }
//    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto.CreateResUser> signup(@RequestBody UserDto.CreateReqUser req) {
        return ResponseEntity.ok(userService.CreateUser(req));
    }


}
