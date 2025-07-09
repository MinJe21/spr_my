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

    @Column(nullable = true)
    private String duedate;

    @Column(unique = true, nullable = false, length = 512)
    private String content;

    public static RefreshToken of(Long userId, String duedate, String content) {
        return new  RefreshToken(null, userId, duedate, content);
    }

}
