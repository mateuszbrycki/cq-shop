package com.cqshop.usermanagement.application.handler;

import com.cqshop.cqrs.common.handler.command.CommandHandlerAnnotation;
import com.cqshop.cqrs.common.handler.command.CommandHandler;
import com.cqshop.usermanagement.application.command.AccountRemovalRequested;
import com.cqshop.usermanagement.domain.exception.UserNotFoundException;
import com.cqshop.usermanagement.domain.service.UserDeletionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Mateusz Brycki on 03/10/2018.
 */
@Slf4j
@RequiredArgsConstructor
@CommandHandlerAnnotation
public class AccountRemovalRequestedHandler implements CommandHandler<AccountRemovalRequested, Boolean> {

    private final UserDeletionService userDeletionService;

    @Override
    public Boolean handle(AccountRemovalRequested accountRemovalRequested) {

        log.info("Received accountRemovalRequested: " + accountRemovalRequested);

        try {
            return userDeletionService.delete(
                    accountRemovalRequested.getUserId());

        } catch (UserNotFoundException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
