package com.example.my.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id") // DB 테이블에 user_id 컬럼 생성됨
    private User user;

    public static Post from(String title, String content, User user){
        return new Post(null, title, content, user);
    }

    public static Post of(Long userId, String title, String content, User user) {
        return new Post(userId, title, content, user);
    }
}
