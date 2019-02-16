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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 2019-02-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountActivationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountActivationCodeRepository accountActivationCodeRepository;

    @Mock
    private EventPublisher eventPublisher;

    @InjectMocks
    private AccountActivationService accountActivationService;

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundExceptionWhenUserDoesNotExist() throws AccountActivationCodeNotFound, UserNotFoundException {

        //given
        when(userRepository.findById(9999l)).thenReturn(Optional.empty());

        //when
        accountActivationService.activateAccountWithActivationCode(9999l, "just-random-activation-code");
    }

    @Test(expected = AccountActivationCodeNotFound.class)
    public void shouldThrowActivationCodeNotFoundExceptionWhenUserDoesNotExist() throws AccountActivationCodeNotFound, UserNotFoundException {

        //given
        User user = User.builder().build();
        when(userRepository.findById(1l)).thenReturn(Optional.of(user));
        when(accountActivationCodeRepository.findByUser(user)).thenReturn(Optional.empty());

        //when
        accountActivationService.activateAccountWithActivationCode(1, "just-random-activation-code");
    }

    @Test
    public void shouldNotActivateAccountWhenCodesAreNotEqual() throws AccountActivationCodeNotFound, UserNotFoundException {

        //given
        User user = User.builder().build();
        when(userRepository.findById(1l)).thenReturn(Optional.of(user));
        when(accountActivationCodeRepository.findByUser(user)).thenReturn(Optional.of(
                AccountActivationCode.builder().user(user).activationCode("activation-code").build()));

        //when
        Boolean result = accountActivationService.activateAccountWithActivationCode(1, "just-random-activation-code");

        //then
        assertFalse(result);
    }

    @Test
    public void shouldActivateAccountWhenCodesAreEqual() throws AccountActivationCodeNotFound, UserNotFoundException {

        //given
        User user = User.builder().userId(1l).build();
        when(userRepository.findById(1l)).thenReturn(Optional.of(user));
        when(accountActivationCodeRepository.findByUser(user)).thenReturn(Optional.of(
                AccountActivationCode.builder().user(user).activationCode("activation-code").build()));

        //when
        Boolean result = accountActivationService.activateAccountWithActivationCode(1, "activation-code");

        //then
        assertTrue(result);

        verify(userRepository).save(User.builder().userId(1l).status(User.Status.ACTIVATED).build());
        verify(eventPublisher).publish(UserAccountActivated.builder()
                .userId(user.getUserId())
                .build());
    }

    @Test
    public void shouldCreateActivationCode() {
        //given
        User user = User.builder().userId(1l).build();
        String activationCodeString = String.valueOf((user.getUserId().toString() + "-activation").hashCode());

        AccountActivationCode activationCode = AccountActivationCode.builder()
                .user(user)
                .activationCode(activationCodeString)
                .build();

        when(accountActivationCodeRepository.save(activationCode)).thenReturn(activationCode);

        //when
        Boolean result = accountActivationService.generateActivationCodeForRegisteredUser(user);

        //then
        assertTrue(result);
        verify(accountActivationCodeRepository).save(activationCode);

        verify(eventPublisher).publish(AccountActivationCodeCreated.builder()
                .userId(user.getUserId())
                .activationCode(activationCodeString)
                .build());
    }







}