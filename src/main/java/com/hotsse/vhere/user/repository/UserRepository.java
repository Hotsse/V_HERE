package com.hotsse.vhere.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotsse.vhere.user.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
