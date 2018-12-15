package com.cqshop.logging.usermanagement;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */

import com.cqshop.usermanagement.avro.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserManagementEventsListener {

    @StreamListener(target = UserManagementStreamsConfig.USER_MANAGEMENT_EVENTS, condition = "headers['event-type']=='UserAccountCreated'")
    public void handleUserAccountCreated(UserAccountCreated event) {
        log.info("Received UserAccountCreated event " + event.toString());
    }

    @StreamListener(target = UserManagementStreamsConfig.USER_MANAGEMENT_EVENTS, condition = "headers['event-type']=='AccountActivationCodeCreated'")
    public void handleAccountActivationCodeCreated(AccountActivationCodeCreated event) {
        log.info("Received AccountActivationCodeCreated event " + event.toString());
    }

    @StreamListener(target = UserManagementStreamsConfig.USER_MANAGEMENT_EVENTS, condition = "headers['event-type']=='UserAccountActivated'")
    public void handleUserAccountActivated(UserAccountActivated event) {
        log.info("Received UserAccountActivated event " + event.toString());
    }

    @StreamListener(target = UserManagementStreamsConfig.USER_MANAGEMENT_EVENTS, condition = "headers['event-type']=='UserAccountUpdated'")
    public void handleUserAccountUpdated(UserAccountUpdated event) {
        log.info("Received UserAccountUpdated event " + event.toString());
    }

    @StreamListener(target = UserManagementStreamsConfig.USER_MANAGEMENT_EVENTS, condition = "headers['event-type']=='UserAccountRemoved'")
    public void handleUserAccountRemoved(UserAccountRemoved event) {
        log.info("Received UserAccountRemoved event " + event.toString());
    }


}
