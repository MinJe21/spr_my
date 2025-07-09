package com.example.my.repository;

import com.example.my.domain.UserRoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleTypeRepository extends JpaRepository<UserRoleType, String> {
}