package com.cqshop.notification.domain.service;

import com.cqshop.notification.domain.event.ActivationLinkSent;
import com.cqshop.notification.infrastructure.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Mateusz Brycki on 24/11/2018.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MailSenderService {

    private final EventPublisher eventPublisher;


    public void sendActivationLink(String username, String email) {

        //sending an email

        eventPublisher.publish(
                ActivationLinkSent.builder()
                .email(email)
                .username(username)
                .build()
        );
    }
}
