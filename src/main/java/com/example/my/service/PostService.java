package com.example.my.service;

import com.example.my.domain.Post;
import com.example.my.domain.User;
import com.example.my.dto.PostDto;
import com.example.my.repository.PostRepository;
import com.example.my.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostDto.CreateResPost createPost(PostDto.CreateReqPost req) {
        User user = userRepository.findByUserId(req.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        Post post = Post.from(req.getTitle(), req.getContent(), user);
        Post saved = postRepository.save(post);

        return new PostDto.CreateResPost(saved.getPostId(), saved.getTitle(), saved.getContent());
    }

    public List<PostDto.ReadResPost> readPost(PostDto.ReadReqPost req) {
        User u = userRepository.findByUserId(req.getUserId())
                .orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없습니다."));

        List<Post> postList = postRepository.findAllByUser(u);

        List<PostDto.ReadResPost> dtoList = new ArrayList<>();
        for (Post post : postList) {
            PostDto.ReadResPost dto = PostDto.ReadResPost.builder()
                    .postId(post.getPostId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .build();
            dtoList.add(dto);
        }

        return dtoList;
    }

    public PostDto.UpdateResPost updatePost(PostDto.UpdateServPost req) {
        Post post = postRepository.findById(req.getPostId())
                .orElseThrow(() -> new RuntimeException("post not found"));

        if (!post.getUser().getUserId().equals(req.getReqUserId())) {
            throw new RuntimeException("not matched userId");
        }

        post.setTitle(req.getTitle());
        post.setContent(req.getContent());
        postRepository.save(post);

        return PostDto.UpdateResPost.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }


//    public void updatePost(PostDto.UpdateServPost req) {
//        Post post = postRepository.findById(req.getReqUserId()).orElse(null);
//        Long reqUserId = req.getReqUserId();
//        if(post == null) {
//            throw new RuntimeException("no post");
//        }
//        if(post.getPostId() == null) {
//            throw new RuntimeException("not login");
//        }
//        if(!reqUserId.equals(post.getUser().getUserId())) {
//            throw new RuntimeException("not matched userId");
//        }
//
//        if(post.getTitle() == null) {
//            post.setTitle(req.getTitle());
//        }
//        if(post.getContent() == null) {
//            post.setContent(req.getContent());
//        }
//        postRepository.save(post);
//    }
}
