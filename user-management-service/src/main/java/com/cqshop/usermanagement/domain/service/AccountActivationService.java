package com.cqshop.usermanagement.domain.service;

import com.cqshop.usermanagement.domain.AccountActivationCode;
import com.cqshop.usermanagement.domain.User;
import com.cqshop.usermanagement.domain.event.AccountActivationCodeCreated;
import com.cqshop.usermanagement.domain.event.UserAccountActivated;
import com.cqshop.usermanagement.domain.exception.AccountActivationCodeNotFound;
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


    public Boolean activateAccountWithActivationCode(User user, String activationCode) throws AccountActivationCodeNotFound {

        if (user == null) {
            log.error("Got empty user account");

            return false;
        }

        AccountActivationCode userActivationCode = accountActivationCodeRepository.findByUserId(user.getID())
                .orElseThrow(() -> new AccountActivationCodeNotFound("Activation code for user " + user.getID() + " not found"));

        if (!areCodesEqual(userActivationCode, activationCode)) {
            return false;
        }

        activateAccount(user);
        eventPublisher.publish(UserAccountActivated.builder()
                .userId(user.getID())
                .build()
        );

        return true;

    }

    public Boolean generateActivationCodeForRegisteredUser(User user) {

        String activationCode = generateActivationCode(user);

        AccountActivationCode accountActivationCode = AccountActivationCode.builder()
                .userId(user.getID())
                .activationCode(activationCode)
                .build();

        accountActivationCode = accountActivationCodeRepository.save(accountActivationCode);

        eventPublisher.publish(AccountActivationCodeCreated.builder()
                .userId(accountActivationCode.getUserId())
                .activationCode(accountActivationCode.getActivationCode())
                .build()
        );

        return true;
    }

    private String generateActivationCode(User user) {
        return String.valueOf((user.getID().toString() + "-activation").hashCode());
    }

    private boolean areCodesEqual(AccountActivationCode userActivationCode, String activationCode) {
        return userActivationCode.getActivationCode().equals(activationCode);
    }

    private void activateAccount(User user) {
        user.setStatus(User.Status.ACTIVATED);
        userRepository.save(user);
    }
}
