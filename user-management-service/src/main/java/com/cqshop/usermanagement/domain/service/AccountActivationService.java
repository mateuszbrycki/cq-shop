package com.cqshop.usermanagement.domain.service;

import com.cqshop.usermanagement.domain.AccountActivationCode;
import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.event.AccountActivationCodeCreated;
import com.cqshop.usermanagement.domain.event.UserAccountActivated;
import com.cqshop.usermanagement.domain.exception.AccountActivationCodeNotFound;
import com.cqshop.usermanagement.domain.exception.UserNotFoundException;
import com.cqshop.usermanagement.domain.repository.AccountActivationCodeRepository;
import com.cqshop.usermanagement.domain.repository.UserRepository;
import com.cqshop.usermanagement.infrastructure.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class AccountActivationService {

    private final UserRepository userRepository;
    private final AccountActivationCodeRepository accountActivationCodeRepository;
    private final EventPublisher eventPublisher;


    public Boolean activateAccountWithActivationCode(long userId, String activationCode) throws AccountActivationCodeNotFound, UserNotFoundException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        AccountActivationCode userActivationCode = accountActivationCodeRepository.findByUser(user)
                .orElseThrow(() -> new AccountActivationCodeNotFound("Activation code for user " + user.getUserId() + " not found"));

        if (!areCodesEqual(userActivationCode, activationCode)) {
            return false;
        }

        activateAccount(user);
        eventPublisher.publish(UserAccountActivated.builder()
                .userId(user.getUserId())
                .build()
        );

        return true;

    }

    public Boolean generateActivationCodeForRegisteredUser(User user) {

        String activationCode = generateActivationCode(user);

        AccountActivationCode accountActivationCode = AccountActivationCode.builder()
                .user(user)
                .activationCode(activationCode)
                .build();

        accountActivationCode = accountActivationCodeRepository.save(accountActivationCode);

        eventPublisher.publish(AccountActivationCodeCreated.builder()
                .userId(accountActivationCode.getUser().getUserId())
                .activationCode(accountActivationCode.getActivationCode())
                .build()
        );

        return true;
    }

    private String generateActivationCode(User user) {
        return String.valueOf((user.getUserId().toString() + "-activation").hashCode());
    }

    private boolean areCodesEqual(AccountActivationCode userActivationCode, String activationCode) {
        return userActivationCode.getActivationCode().equals(activationCode);
    }

    private void activateAccount(User user) {
        user.setStatus(User.Status.ACTIVATED);
        userRepository.save(user);
    }
}
