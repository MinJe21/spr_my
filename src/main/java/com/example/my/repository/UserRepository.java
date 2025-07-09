package com.example.my.repository;

import com.example.my.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserId(Long userId);
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);

    @EntityGraph(attributePaths = {"userRoleType.roleType"})
    Optional<User> findEntityGraphRoleTypeByUserId(Long id);
}
