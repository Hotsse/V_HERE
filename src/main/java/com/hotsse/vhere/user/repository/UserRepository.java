package com.hotsse.vhere.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hotsse.vhere.user.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "SELECT u FROM User u WHERE u.id = :id AND useYn = 'Y'")
	Optional<User> findAvailableById(String id);
}
