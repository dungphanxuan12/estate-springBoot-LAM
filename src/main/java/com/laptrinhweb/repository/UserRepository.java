package com.laptrinhweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhweb.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
