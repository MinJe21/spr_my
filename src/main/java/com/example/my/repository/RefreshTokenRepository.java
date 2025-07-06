package com.example.my.repository;

import com.example.my.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {
    RefreshToken findByContent(String content);
    List<RefreshToken> findByUserId(Long userId);
}