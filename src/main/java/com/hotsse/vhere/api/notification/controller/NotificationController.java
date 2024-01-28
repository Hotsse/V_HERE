package com.hotsse.vhere.api.notification.controller;

import com.hotsse.vhere.api.notification.service.NotificationService;
import com.hotsse.vhere.core.base.BaseController;
import com.hotsse.vhere.user.dto.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/notification")
@RequiredArgsConstructor
public class NotificationController extends BaseController {

    private final NotificationService notificationService;

    @PostMapping("/messaging-token/renew")
    public ResponseEntity<Void> renewMessagingToken(@RequestParam(name = "token") String token) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser user = (SecurityUser) auth.getPrincipal();
        String id = user.getUsername();

        notificationService.renewUserMessagingToken(id, token);

        return ResponseEntity.created(null).build();
    }
}
