package com.cqshop.usermanagement.application.handler;

import com.cqshop.cqrs.common.handler.CommandHandler;
import com.cqshop.cqrs.common.handler.CommandHandlerAnnotation;
import com.cqshop.usermanagement.application.command.UpdateAccountDetailsProvided;
import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.exception.UserNotFoundException;
import com.cqshop.usermanagement.domain.service.AccountUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Mateusz Brycki on 03/10/2018.
 */
@Slf4j
@RequiredArgsConstructor
@CommandHandlerAnnotation
public class UpdateAccountDetailsProvidedHandler implements CommandHandler<UpdateAccountDetailsProvided, Boolean> {

    private final AccountUpdateService accountUpdateService;

    @Override
    public Boolean handle(UpdateAccountDetailsProvided accountDetailsProvided) {

        log.info("Received updateAccountDetailsProvided: " + accountDetailsProvided);

        User user = User.builder()
                .userId(accountDetailsProvided.getUserId())
                .username(accountDetailsProvided.getUsername())
                .password(accountDetailsProvided.getPassword())
                .build();

        try {
            return accountUpdateService.updateAccount(user);
        } catch (
                UserNotFoundException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
