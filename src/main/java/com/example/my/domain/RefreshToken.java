package com.example.my.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String duedate;

    @Column(unique = true, nullable = false, length = 512)
    private String content;

    public static RefreshToken of(Long userId, String duedate, String content) {
        return RefreshToken.builder()
                .userId(userId)
                .duedate(duedate)
                .content(content)
                .build();
    }

}
