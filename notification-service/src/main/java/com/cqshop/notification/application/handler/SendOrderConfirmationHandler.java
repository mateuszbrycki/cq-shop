package com.cqshop.notification.application.handler;

import com.cqshop.cqrs.common.handler.command.CommandHandler;
import com.cqshop.cqrs.common.handler.command.CommandHandlerAnnotation;
import com.cqshop.notification.application.command.SendActivationLink;
import com.cqshop.notification.application.command.SendOrderConfirmation;
import com.cqshop.notification.domain.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Mateusz Brycki on 24/11/2018.
 */
@Slf4j
@RequiredArgsConstructor
@CommandHandlerAnnotation
public class SendOrderConfirmationHandler implements CommandHandler<SendOrderConfirmation, Void> {

    private final MailSenderService mailSenderService;

    @Override
    public Void handle(SendOrderConfirmation sendOrderConfirmation) {
        log.info("Received sendOrderConfirmation: " + sendOrderConfirmation);
        mailSenderService.sendOrderConfirmation(sendOrderConfirmation.getOrderId(), sendOrderConfirmation.getUserId());

        return null;
    }
}
