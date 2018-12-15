package com.cqshop.usermanagement.application.handler;

import com.cqshop.cqrs.common.handler.CommandHandler;
import com.cqshop.cqrs.common.handler.CommandHandlerAnnotation;
import com.cqshop.usermanagement.application.command.ActivationLinkClicked;
import com.cqshop.usermanagement.domain.exception.AccountActivationCodeNotFound;
import com.cqshop.usermanagement.domain.exception.UserNotFoundException;
import com.cqshop.usermanagement.domain.service.AccountActivationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
@Slf4j
@RequiredArgsConstructor
@CommandHandlerAnnotation
public class ActivationLinkClickedHandler implements CommandHandler<ActivationLinkClicked, Boolean> {

    private final AccountActivationService accountActivationService;

    @Override
    public Boolean handle(ActivationLinkClicked activationLinkClicked) {

        log.info("Received activationLinkClicked: " + activationLinkClicked);

        try {
            return accountActivationService.activateAccountWithActivationCode(
                    activationLinkClicked.getUserId(), activationLinkClicked.getActivationCode()
            );

        } catch (UserNotFoundException | AccountActivationCodeNotFound e) {
            log.error(e.getMessage());
            return false;
        }

    }
}
