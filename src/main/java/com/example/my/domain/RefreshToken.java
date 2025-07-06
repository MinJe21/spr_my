package com.example.my.domain;

import com.example.my.dto.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
public class RefreshToken{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;

    Boolean deleted;
    String duedate;

    @Column(unique=true)
    String content;

    protected RefreshToken(){}
    private RefreshToken(Boolean deleted, Long userId, String duedate, String content){
        this.deleted = deleted;
        this.userId = userId;
        this.duedate = duedate;
        this.content = content;
    }
    public static RefreshToken of(Long userId, String duedate, String content){
        return new RefreshToken(false, userId, duedate, content);
    }
    public UserDto.CreateResUser toCreateResDto() {
        return UserDto.CreateResUser.builder().id(getUserId()).build();
    }
}
