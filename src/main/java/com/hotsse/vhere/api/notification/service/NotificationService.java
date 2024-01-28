package com.hotsse.vhere.api.notification.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.hotsse.vhere.api.notification.entity.UserMessageToken;
import com.hotsse.vhere.api.notification.repository.UserMessageTokenRepository;
import com.hotsse.vhere.core.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService extends BaseService {

    private final UserMessageTokenRepository userMessageTokenRepository;

    @Transactional
    public void renewUserMessagingToken(String id, String token) {
        userMessageTokenRepository.save(new UserMessageToken(token, id));
    }

    public void sendPushNotification(String id) {

        userMessageTokenRepository.findAllByUserId(id).forEach(tokenEntity -> {
            Notification notification = Notification.builder()
                    .setTitle("당신의 브이가 좋아요를 받았어요!")
                    .setBody("여기를 눌러 확인해 보세요 :)")
                    .build();
            Message message = Message.builder()
                    .setToken(tokenEntity.getToken())
                    .setNotification(notification)
                    .build();

            try {
                FirebaseMessaging.getInstance().send(message);
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
            }
        });
    }
}
