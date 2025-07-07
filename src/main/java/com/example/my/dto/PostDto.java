package com.example.my.dto;

import com.example.my.domain.Post;
import com.example.my.domain.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class PostDto {
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReqPost{
        private Long userId;
        private String title;
        private String content;

        public Post toEntity(){
            return Post.of( getTitle(), getContent(), null);
        }
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResPost {
        private Long postId;
        private String title;
        private String content;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReadReqPost {
        private Long userId;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReadResPost {
        private Long postId;
        private String title;
        private String content;
    }

    @SuperBuilder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateReqPost {
        private Long userId;
        private Long postId;
        private String title;
        private String content;
    }

    @SuperBuilder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateServPost extends UpdateReqPost{
        Long reqUserId;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateResPost {
        private Long postId;
        private String title;
        private String content;
    }
}

