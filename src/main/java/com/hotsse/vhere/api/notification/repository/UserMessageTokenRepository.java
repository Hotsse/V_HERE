package com.hotsse.vhere.api.notification.repository;

import com.hotsse.vhere.api.notification.entity.UserMessageToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserMessageTokenRepository extends JpaRepository<UserMessageToken, String> {

    public List<UserMessageToken> findAllByUserId(String userId);
}
