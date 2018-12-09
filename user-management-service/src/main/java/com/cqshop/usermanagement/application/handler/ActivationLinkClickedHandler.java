package com.cqshop.usermanagement.application.handler;

import com.cqshop.cqrs.common.handler.CommandHandler;
import com.cqshop.cqrs.common.handler.CommandHandlerAnnotation;
import com.cqshop.usermanagement.application.command.ActivationLinkClicked;
import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.exception.AccountActivationCodeNotFound;
import com.cqshop.usermanagement.domain.exception.UserNotFoundException;
import com.cqshop.usermanagement.domain.service.AccountActivationService;
import com.cqshop.usermanagement.domain.service.UserRegistrationService;
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
    private final UserRegistrationService userRegistrationService;

    @Override
    public Boolean handle(ActivationLinkClicked activationLinkClicked) {

        log.info("Received activationLinkClicked: " + activationLinkClicked);

        try {

            User user = userRegistrationService.findById(activationLinkClicked.getUserId());
            return accountActivationService.activateAccountWithActivationCode(user, activationLinkClicked.getActivationCode());

        } catch (UserNotFoundException | AccountActivationCodeNotFound e) {
            log.error(e.getMessage());
            return false;
        }

    }
}
