package com.example.my.controller;

import com.example.my.exception.InvalidTokenException;
import com.example.my.security.AuthService;
import com.example.my.security.ExternalProperties;
import com.example.my.util.TokenFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthRestController {

    //private final TokenFactory tokenFactory;
    //private final String prefix = "Bearer ";

    final ExternalProperties externalProperties;
    final AuthService authService;

    @PostMapping("")
    public ResponseEntity<Void> access(HttpServletRequest request){
        String accessToken = null;
        String prefix = externalProperties.getTokenPrefix();
        String refreshToken = request.getHeader(externalProperties.getRefreshKey());
        if(refreshToken == null || !refreshToken.startsWith(prefix)){
            throw new InvalidTokenException("no prefix");
        }
        refreshToken = refreshToken.substring(prefix.length());
        accessToken = authService.issueAccessToken(refreshToken);
        return ResponseEntity.status(HttpStatus.OK).header(externalProperties.getAccessKey(), prefix + accessToken).build();
    }

}