package com.hotsse.vhere.api.notification.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserMessageToken {

    @Id
    @Column(length = 255)
    private String token;

    @Column(length = 50)
    private String userId;

    public UserMessageToken(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }
}
