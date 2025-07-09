package com.example.my.controller;

import com.example.my.dto.PostDto;
import com.example.my.security.PrincipalDetails;
import com.example.my.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public Long getReqUserId(@AuthenticationPrincipal PrincipalDetails principalDetails){
        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getUserId() == null){
            return null;
        }
        return principalDetails.getUser().getUserId();
    }

    @PostMapping("/create")
    public ResponseEntity<PostDto.CreateResPost> createPost(@RequestBody PostDto.CreateReqPost params,
                                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = getReqUserId(principalDetails);
        params.setUserId(reqUserId);
        System.out.println("요청한 유저 아이디: " + reqUserId);
        return ResponseEntity.ok(postService.createPost(params));
    }

    @GetMapping("/read")
    public ResponseEntity<List<PostDto.ReadResPost>> readPost(@ModelAttribute PostDto.ReadReqPost params,
                                                              @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<PostDto.ReadResPost> responseDtoList = postService.readPost(params);
        return ResponseEntity.ok(responseDtoList);
    }

    @PutMapping("/update")
    public ResponseEntity<PostDto.UpdateResPost> updatePost(@RequestBody PostDto.UpdateServPost params,
                                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = getReqUserId(principalDetails);
        params.setReqUserId(reqUserId);
        System.out.println("요청한 유저 아이디: " + reqUserId);
        postService.updatePost(params);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
