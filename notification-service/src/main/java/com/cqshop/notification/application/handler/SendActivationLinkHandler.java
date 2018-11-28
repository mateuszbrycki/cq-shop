package com.cqshop.notification.application.handler;

import com.cqshop.cqrs.common.handler.CommandHandler;
import com.cqshop.cqrs.common.handler.CommandHandlerAnnotation;
import com.cqshop.notification.application.command.SendActivationLink;
import com.cqshop.notification.domain.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Mateusz Brycki on 24/11/2018.
 */
@Slf4j
@RequiredArgsConstructor
@CommandHandlerAnnotation
public class SendActivationLinkHandler implements CommandHandler<SendActivationLink, Void> {

    private final MailSenderService mailSenderService;

    @Override
    public Void handle(SendActivationLink sendActivationLink) {
        log.info("Received sendActivationLink: " + sendActivationLink);
        mailSenderService.sendActivationLink(sendActivationLink.getUsername(), sendActivationLink.getEmail());
        return null;
    }
}
